package goorm.tricount.exception.exhandler;

import goorm.tricount.api.ErrorCode;
import goorm.tricount.api.response.ApiResponse;
import goorm.tricount.exception.users.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "goorm.tricount.controller")
public class ExUserControllerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UserHttpNotFoundException.class)
    public ApiResponse<Object> userHttpNotFoundExHandler(UserHttpNotFoundException e){
        log.warn("[UserHttpNotFoundException] ex", e);
        return new ApiResponse<Object>(ErrorCode.NOT_FOUND, "유저 테이블을 찾을 수 없습니다.");

    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UserConflitException.class)
    public ApiResponse<Object> userConflictExHandler(UserConflitException e){
        log.warn("[UserConflictException] ex", e);
        return new ApiResponse<Object>(ErrorCode.CONFLICT, "이미 존재하는 아이디 입니다.");

    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UserUnprocessableEntityException.class)
    public ApiResponse<Object> userUnprocessableEntityExHandler(UserUnprocessableEntityException e){
        log.warn("[UserUnprocessableEntityException] ex", e);
        return new ApiResponse<Object>(ErrorCode.UNPROCESSABLE_ENTITY, "아이디와 비밀번호는 8이상 20자 이하, 닉네임은 2자 이상 20자 이하여야 합니다");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UserUnauthorizedException.class)
    public ApiResponse<Object> userUnauthorizedExHandler(UserUnauthorizedException e){
        log.warn("[UserUnauthorizedException] ex", e);
        return new ApiResponse<Object>(ErrorCode.LOGIN_UNAUTHORIZED, "로그인 정보가 올바르지 않습니다. 아이디와 비밀번호를 다시 확인해주세요.");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ForbiddenException.class)
    public ApiResponse<Object> forbiddenExHandler(ForbiddenException e){
        log.warn("[ForbiddenException] ex", e);
        return new ApiResponse<Object>(ErrorCode.FORBIDDEN, "로그인 후 이용 부탁드립니다.");
    }

}
