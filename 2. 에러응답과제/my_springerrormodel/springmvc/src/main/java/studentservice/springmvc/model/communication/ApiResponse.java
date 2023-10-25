package studentservice.springmvc.model.communication;



import com.fasterxml.jackson.annotation.JsonInclude;
import studentservice.springmvc.exception.ErrorCode;

import java.util.List;

@lombok.Data
public class ApiResponse<T> {
    private final Status status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Metadata metadata;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<T> results;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Data data;


    // addStudent
    public ApiResponse(Metadata metadata, List<T> results) {
        this.status = new Status(ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
        this.metadata = metadata;
        this.results = results;
    }


    // error
    public ApiResponse(Status status, Data data) {
        this.status = new Status(status.getCode(), status.getMessage());
        this.data = data;
    }
}
