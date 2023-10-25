package studentservice.springmvc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    OK(2000,"OK", HttpStatus.OK),
    NOT_FOUND(5000, "BAD REQUEST", HttpStatus.OK),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;


}
