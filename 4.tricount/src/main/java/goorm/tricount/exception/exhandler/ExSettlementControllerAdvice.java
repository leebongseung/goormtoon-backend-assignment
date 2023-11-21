package goorm.tricount.exception.exhandler;


import goorm.tricount.api.ErrorCode;
import goorm.tricount.api.response.ApiResponse;
import goorm.tricount.exception.settlement.SettlementHttpNotFoundException;
import goorm.tricount.exception.settlement.UserHasSettlementConflictException;
import goorm.tricount.exception.settlement.UsersHasSettlementHttpNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "goorm.tricount.controller")
public class ExSettlementControllerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UsersHasSettlementHttpNotFoundException.class)
    public ApiResponse<Object> usersHasSettlementHttpNotFoundExHandler(UsersHasSettlementHttpNotFoundException e){
        log.warn("[UsersHasSettlementHttpNotFoundException] ex", e);
        return new ApiResponse<Object>(ErrorCode.UNAUTHORIZED, "지출에 포함된 유저 테이블을 찾을 수 없습니다.");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SettlementHttpNotFoundException.class)
    public ApiResponse<Object> settlementHttpNotFoundExHandler(SettlementHttpNotFoundException e){
        log.warn("[SettlementHttpNotFoundException] ex", e);
        return new ApiResponse<Object>(ErrorCode.UNAUTHORIZED, "지출 테이블을 찾을 수 없습니다.");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UserHasSettlementConflictException.class)
    public ApiResponse<Object> userHasSettlementConflictExHandler(UserHasSettlementConflictException e){
        log.warn("[UserHasSettlementConflictException] ex", e);
        return new ApiResponse<Object>(ErrorCode.CONFLICT, "이미 참여한 settlement입니다.");
    }
}
