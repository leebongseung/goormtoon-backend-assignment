package goorm.tricount.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import goorm.tricount.api.ErrorCode;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
public class ApiResponse<T> {
    private int code;
    private String status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<T> results = new ArrayList<>();

    public ApiResponse(ErrorCode errorCode, List<T> results) {
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.results = results;
    }

    public ApiResponse(ErrorCode errorCode, T message) {
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.message= message;
    }
    public ApiResponse<T> ok(T message){
        this.code = ErrorCode.OK.getCode();
        this.status = ErrorCode.OK.getStatus();
        this.message = message;
        return this;
    }
}