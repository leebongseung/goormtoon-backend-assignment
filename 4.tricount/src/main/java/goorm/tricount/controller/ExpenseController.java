package goorm.tricount.controller;

import goorm.tricount.annotation.Login;
import goorm.tricount.api.ErrorCode;
import goorm.tricount.api.response.ApiResponse;
import goorm.tricount.domain.dto.request.expense.ExpenseRequestDto;
import goorm.tricount.domain.dto.response.expense.BalanceDto;
import goorm.tricount.domain.dto.response.expense.ExpenseDetailDto;
import goorm.tricount.domain.dto.response.expense.ExpensesDto;
import goorm.tricount.service.ExpenseService;
import goorm.tricount.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/settle/{settlementId}")
@RequiredArgsConstructor
public class ExpenseController {

    final private ExpenseService expenseService;

    @GetMapping("/expenses")
    public ApiResponse<ExpensesDto> findAllExpenses(@PathVariable Long settlementId){
        ExpensesDto allExpenses = expenseService.findAllExpenses(settlementId);
        return new ApiResponse<>(ErrorCode.OK, allExpenses);
    }

    @PostMapping("/expenses/add")
    public ApiResponse<Object> addExpense
            (@PathVariable Long settlementId,
             @Valid @RequestBody ExpenseRequestDto expenseRequestDto ,
             @Login Long usersId){
        ExpenseDetailDto expenseDetailDto = expenseService.addExpense(expenseRequestDto, usersId, settlementId);
        return new ApiResponse<>(ErrorCode.OK, expenseDetailDto);
    }

    @DeleteMapping("/expenses/{expenseNo}")
    public ApiResponse<Object> deleteExpense
            (@PathVariable Long settlementId,
             @PathVariable Long expenseNo,
             @Login Long usersId){
        expenseService.deleteExpense(expenseNo, usersId, settlementId);
        return new ApiResponse<>(ErrorCode.OK, "결제 목록이 삭제되었습니다");
    }

    @GetMapping("/balance")
    public ApiResponse<Object> balance(@PathVariable Long settlementId, @Login Long usersId){
        List<BalanceDto> balance = expenseService.balance(usersId, settlementId);
        return new ApiResponse<Object>(ErrorCode.OK, balance);
    }


}
