package goorm.tricount.exception.exhandler;

import goorm.tricount.api.ErrorCode;
import goorm.tricount.api.response.ApiResponse;
import goorm.tricount.exception.expense.ExpenseUnprocessableEntityException;
import goorm.tricount.exception.expense.ExpenseAssociatedUserNotFoundException;
import goorm.tricount.exception.expense.ExpenseNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "goorm.tricount.controller")
public class ExExpenseControllerAdvice {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ExpenseNotFoundException.class)
    public ApiResponse<Object> expenseNotFoundExHandler(ExpenseNotFoundException e){
        log.warn("[ExpenseNotFoundException] ex", e);
        return new ApiResponse<Object>(ErrorCode.NOT_FOUND, "결제 목록을 찾을 수 없습니다.");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ExpenseAssociatedUserNotFoundException.class)
    public ApiResponse<Object> expenseAssociatedUserNotFoundExHandler(ExpenseAssociatedUserNotFoundException e){
        log.warn("[ExpenseAssociatedUserNotFoundException] ex", e);
        return new ApiResponse<Object>(ErrorCode.NOT_FOUND, "결제 목록에 포함된 사람들을 찾을 수 없습니다.");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ExpenseUnprocessableEntityException.class)
    public ApiResponse<Object> expenseUnprocessableEntityExHandler(ExpenseUnprocessableEntityException e){
        log.warn("[expenseUnprocessableEntityExHandler] ex", e);
        return new ApiResponse<Object>(ErrorCode.UNPROCESSABLE_ENTITY, "결제 목록에 포함된 사람이 없거나, 포함된 사람을 찾을 수 없습니다.");
    }
}
