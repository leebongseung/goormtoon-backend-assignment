package studentservice.springmvc.model.communication;


import lombok.Data;

@Data
public class Status {
    private final int code;
    private final String message;

    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
