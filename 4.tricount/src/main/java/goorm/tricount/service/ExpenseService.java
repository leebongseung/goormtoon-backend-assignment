package goorm.tricount.service;

import goorm.tricount.domain.dto.request.expense.ExpenseRequestDto;
import goorm.tricount.domain.dto.response.expense.BalanceDto;
import goorm.tricount.domain.dto.response.expense.ExpenseDetailDto;
import goorm.tricount.domain.dto.response.expense.ExpenseDto;
import goorm.tricount.domain.dto.response.expense.ExpensesDto;
import goorm.tricount.domain.entity.expense.Expense;
import goorm.tricount.domain.entity.expense.ExpenseAssociatedUser;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import goorm.tricount.domain.entity.user.Users;
import goorm.tricount.exception.expense.ExpenseNotFoundException;
import goorm.tricount.exception.expense.ExpenseUnprocessableEntityException;
import goorm.tricount.exception.settlement.SettlementHttpNotFoundException;
import goorm.tricount.exception.settlement.UsersHasSettlementHttpNotFoundException;
import goorm.tricount.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final JpaUserRepository userRepository;
    private final JpaSettlementRepository settlementRepository;
    private final JpaExpenseRepository expenseRepository;
    final private JpaUsersHasSettlementRepository usersHasSettlementRepository;
    private final JpaExpenseAssociatedUserRepository expenseAssociatedUserRepository;


    public ExpensesDto findAllExpenses(Long settlementId) {
        // userId가 settlment에 포함이 안되었을 경우

        // settlement가 없을 경우
        List<UsersHasSettlement> userHasSettlements = usersHasSettlementRepository.findAllByUserIdAndSettlementId(settlementId);
        if (userHasSettlements.isEmpty()) {
            throw new SettlementHttpNotFoundException();
        }

        // UsersHasSettlement에서 모든 지출을 뽑아오기!
        List<ExpenseDto> expenses = userHasSettlements.stream()
                .map(UsersHasSettlement::getExpenseList)
                .flatMap(Collection::stream)
                .distinct()
                .map(ExpenseDto::new)
                .collect(Collectors.toList());

        return new ExpensesDto(expenses);
    }

    @Transactional
    public ExpenseDetailDto addExpense(ExpenseRequestDto expenseRequestDto, Long usersId, Long settlementId) {
        // 유저가 참여한 settlement인지 조사하기
        UsersHasSettlement usersHasSettlement = usersHasSettlementRepository.findByUserIdAndSettlementId(usersId, settlementId).orElseThrow(SettlementHttpNotFoundException::new);

        // ExpenseRequestDto의 expenseAssociatedUserList가 현재 usersHasSettlement에 존재한 id인지 판별해봐야지
        List<Long> usersHasSettlementIdList = expenseRequestDto.getExpenseAssociatedUserList();
        List<UsersHasSettlement> usersHasSettlementList = usersHasSettlementRepository.findAllById(usersHasSettlementIdList);

        //[에러] 유저리스트를 기입안했을 경우
        if (usersHasSettlementIdList.isEmpty() || (usersHasSettlementIdList.size() != usersHasSettlementList.size())) {
            throw new ExpenseUnprocessableEntityException();
        }

        //[에러] 계모임에 포함되지 않은 유저 리스트를 기입했을 경우
        usersHasSettlementList.stream()
                .map(UsersHasSettlement::getId)
                .forEach(id -> {
                    HashSet<Long> longids = new HashSet<>(usersHasSettlementIdList);
                    if (!longids.contains(id)) throw new ExpenseUnprocessableEntityException();
                });


        // expense 생성하기
        Expense expense = new Expense(expenseRequestDto, usersHasSettlement);
        expenseRepository.save(expense);

        // ExpenseAssociatedUser 생성하기
        List<ExpenseAssociatedUser> expenseAssociatedUserList = new ArrayList<>();
        for (UsersHasSettlement hasSettlement : usersHasSettlementList) {
            ExpenseAssociatedUser expenseAssociatedUser = new ExpenseAssociatedUser(expense, hasSettlement);
            expenseAssociatedUserList.add(expenseAssociatedUser);

            // 연관관계매서드 매핑시켜주기
            expenseAssociatedUser.addExpense(expense);
            expenseAssociatedUser.addUsersHasSettlement(hasSettlement);
        }

        // 저장하기
        expenseAssociatedUserRepository.saveAll(expenseAssociatedUserList);

        // expenseDetail 출력하기
        Expense result = expenseRepository.findById(expense.getId()).orElseThrow(ExpenseNotFoundException::new);

        return new ExpenseDetailDto(result);
    }

    @Transactional
    public void deleteExpense(Long expenseNo, Long usersId, Long settlementId) {
        // settlementId와 userId에 해당하는 usersHasSettlment 테이블을 찾기 => 없다면 없는겨
        UsersHasSettlement usersHasSettlement = usersHasSettlementRepository.findByUserIdAndSettlementId(usersId, settlementId).orElseThrow(UsersHasSettlementHttpNotFoundException::new);

        // expense의 paymentUser가 해당 expenseNo에 해당하는지 찾기 => 없다면 없는겨
        Expense expense = expenseRepository.findByUsersHasSettlementId(expenseNo, usersHasSettlement.getId()).orElseThrow(ExpenseNotFoundException::new);

        // 해당한다면 삭제 하기
        expense.deleteExpense();
    }

    public List<BalanceDto> balance(Long usersId, Long settlementId) {
        // expense 에서 fetchjoin으로 모든 expense일단 가져오기
        List<Expense> expenseList = expenseRepository.findAll(settlementId);
        if (expenseList.isEmpty()) {
            throw new ExpenseNotFoundException();
        }

        // receive 사람, send 사람, 정산 dto
        Map<Long, Map<Long, BalanceDto>> balanceDtoList = new ConcurrentHashMap<>(); //받는 사람 ID, 줄사람들

       // 낸사람을 제외한 n명의 사람들에게 각각 총금액/n 의 금액 요구하기
        List<UsersHasSettlement> paymentUserList = expenseList.stream()
                .map(Expense::getPaymentUser)
                .collect(Collectors.toList());

        while (!expenseList.isEmpty()) {
            Expense expense = expenseList.remove(0);

            Users paymentUser = expense.getPaymentUser().getUser();
            List<ExpenseAssociatedUser> expenseAssociatedUserList = expense.getExpenseAssociatedUserList(); // 돈을 내야할 친구들
            BigDecimal amount = expense.getAmount().divide(BigDecimal.valueOf(expenseAssociatedUserList.size()), RoundingMode.DOWN); // 1인당 내야할 금액

            // 금액을 줘야하는 애들
            List<Users> sendUserList = expenseAssociatedUserList.stream()
                    .filter(expenseAssociatedUser -> !expenseAssociatedUser.getUsersHasSettlement().getUser().getUsersId().equals(paymentUser.getUsersId()))
                    .map(expenseAssociatedUser -> expenseAssociatedUser.getUsersHasSettlement().getUser())
                    .collect(Collectors.toList());

            //

            for (Users user : sendUserList) {

                if (balanceDtoList.get(paymentUser.getUsersId()) == null) {
                    balanceDtoList.put(paymentUser.getUsersId(), new ConcurrentHashMap<>());
                }
                balanceDtoList.get(paymentUser.getUsersId())
                        .put(
                                user.getUsersId(),
                                new BalanceDto(user.getUsersId(), user.getNickname(), amount, paymentUser.getUsersId(), paymentUser.getNickname())
                        );

            }
        }



        // 맵 추출해서 금액 비교하기
        for (Long caseAId : balanceDtoList.keySet()) {
            Map<Long, BalanceDto> caseBList = balanceDtoList.get(caseAId);
            for (Long caseBId : caseBList.keySet()) {

                // A => B로 보내는 입장
                if (balanceDtoList.get(caseAId) == null || balanceDtoList.get(caseAId).get(caseBId) == null) continue;
                BalanceDto CaseA = balanceDtoList.get(caseAId).get(caseBId);

                // B => A 보내는 입장
                if (balanceDtoList.get(caseBId) == null || balanceDtoList.get(caseBId).get(caseAId) == null) continue;
                BalanceDto CaseB = balanceDtoList.get(caseBId).get(caseAId);


                BigDecimal minValue = CaseA.getSendAmount().min(CaseB.getSendAmount());
                // CaseA가 더 작은 경우 == B가 A에게 차액을 지불해야함.
                if (minValue.equals(CaseA.getSendAmount())) {
                    // caseA를 삭제하고 caseB의 금액을 수정할 것.
                    balanceDtoList.get(caseAId).remove(caseBId);

                    CaseB.minusAmount(CaseA.getSendAmount());
                    balanceDtoList.get(caseBId).put(caseAId, CaseB);

                } else {  // CaseB가 더 작은 경우 == A가 B에게 차액을 지불해야함.
                    // caseB를 삭제하고 caseA의 금액을 수정할 것.
                    balanceDtoList.get(caseBId).remove(caseAId);

                    CaseA.minusAmount(CaseB.getSendAmount());
                    balanceDtoList.get(caseAId).put(caseBId, CaseA);
                }

            }

        }
        // A -> B -> C -> D 로 보내는 로직을 A->D로 바로 보내게끔 수정하는 WHILE 루프를 작성해야겠네요.. 아 지금? 엄두가안나는데


        List<BalanceDto> res = new ArrayList<>();
        for (Long key1 : balanceDtoList.keySet()) {
            Map<Long, BalanceDto> longBalanceDtoMap = balanceDtoList.get(key1);
            for (Long key2 : longBalanceDtoMap.keySet()) {
                res.add(balanceDtoList.get(key1).get(key2));
            }
        }
        return res;
    }
}
