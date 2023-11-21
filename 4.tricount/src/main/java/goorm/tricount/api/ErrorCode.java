package goorm.tricount.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    OK(2000,"OK", HttpStatus.OK),
    LOGIN_UNAUTHORIZED(4001, "Unauthorized", HttpStatus.OK),
    UNAUTHORIZED(4001, "Unauthorized", HttpStatus.OK),
    FORBIDDEN(4003, "FORBIDDEN", HttpStatus.OK),
    USER_HAS_SETTLEMENT_NOTFOUND(4004, "Not Found", HttpStatus.OK),
    CONFLICT(4009, "CONFLICT", HttpStatus.OK),
    UNPROCESSABLE_ENTITY(4022, "UNPROCESSABLE ENTITY", HttpStatus.OK),
    NOT_FOUND(5000, "BAD REQUEST", HttpStatus.OK)

    ;

    private final int code;
    private final String status;
    private final HttpStatus httpStatus;


}
