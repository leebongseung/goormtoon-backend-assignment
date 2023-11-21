package goorm.tricount.service;

import goorm.tricount.domain.dto.request.settlement.CreateSettlementDto;
import goorm.tricount.domain.dto.response.settlement.SettlementDetailDto;
import goorm.tricount.domain.dto.response.settlement.SettlementDto;
import goorm.tricount.domain.entity.settlement.Settlement;
import goorm.tricount.domain.entity.settlement.SettlementType;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import goorm.tricount.domain.entity.user.Users;
import goorm.tricount.exception.settlement.SettlementHttpNotFoundException;
import goorm.tricount.exception.settlement.UserHasSettlementConflictException;
import goorm.tricount.exception.settlement.UsersHasSettlementHttpNotFoundException;
import goorm.tricount.exception.users.UserHttpNotFoundException;
import goorm.tricount.repository.JpaSettlementRepository;
import goorm.tricount.repository.JpaUserRepository;
import goorm.tricount.repository.JpaUsersHasSettlementRepository;
import goorm.tricount.repository.SettlementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettlementService {
    final private JpaUserRepository userRepository;
    final private  JpaSettlementRepository settlementRepository;
    final private  JpaUsersHasSettlementRepository usersHasSettlementRepository;

    @Transactional
    public SettlementDetailDto createSettlement(Long userId, CreateSettlementDto settlementDto) {
        log.info("SettlementService.createSettlement");
        Users users = userRepository.findById(userId).orElseThrow(UserHttpNotFoundException::new);

        Settlement settlement = settlementRepository.save(new Settlement(settlementDto));
        UsersHasSettlement usersHasSettlement = usersHasSettlementRepository.save(new UsersHasSettlement(settlement, users));
        usersHasSettlement.addSettlement(settlement);
        usersHasSettlement.addUser(users);


        Settlement result = settlementRepository.findById(settlement.getSettlementId()).orElseThrow(SettlementHttpNotFoundException::new);
        return new SettlementDetailDto(result);
    }

    public SettlementDetailDto findSettlementDetailDtoBySettlementId(Long settlementId) {
        log.info("SettlementService.findSettlementDetailDtoBySettlementId");
        Settlement settlement = settlementRepository.findById(settlementId).orElseThrow(SettlementHttpNotFoundException::new);
        List<UsersHasSettlement> usersHasSettlementList = settlement.getUsersHasSettlementList();
//        usersHasSettlementRepository

        return new SettlementDetailDto(settlement);
    }

    public List<SettlementDto> findAllSettlementByUserId(Long userId) {
        log.info("SettlementService.findAllSettlementByUserId");
        List<Settlement> settlements = settlementRepository.findAllByUsersId(userId);
        return settlements.stream()
                .map(SettlementDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public SettlementDetailDto joinSettlementBySettlementId(Long userId,Long settlementId) {
        Users user = userRepository.findById(userId).orElseThrow(UserHttpNotFoundException::new);
        Settlement settlement = settlementRepository.findById(settlementId).orElseThrow(SettlementHttpNotFoundException::new);

        // 이미 조인 되어 있는지 확인하기
        if(!usersHasSettlementRepository.findByUserIdAndSettlementId(user.getUsersId(), settlement.getSettlementId()).isEmpty()){
            throw new UserHasSettlementConflictException();
        }

        // 조인안되었을 경우 추가하기
        UsersHasSettlement usersHasSettlement = usersHasSettlementRepository.save(new UsersHasSettlement(settlement, user));
        usersHasSettlement.addSettlement(settlement);
        usersHasSettlement.addUser(user);
        return new SettlementDetailDto(settlement);
    }
    @Transactional
    public void deleteSettlementBySettlementId(Long settlementId) {
        Settlement settlement = settlementRepository.findById(settlementId).orElseThrow(SettlementHttpNotFoundException::new);
        settlement.setType(SettlementType.DELETE);
        settlementRepository.save(settlement);
    }
}
