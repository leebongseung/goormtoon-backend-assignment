package studentservice.springmvc.exception;

import lombok.Data;
import lombok.Getter;
import studentservice.springmvc.model.communication.InputRestriction;

@Getter
@Data
public class CustomException extends RuntimeException {
    private ErrorCode errorCode;
//    private int code;
//    private String message;
    private Object data;

    public CustomException(ErrorCode errorCode, Object data) {
        this.errorCode = errorCode;
        this.data = data;
    }


    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    public int getCode() {
        return this.errorCode.getCode();
    }

}
