package studentservice.springmvc.model.communication;

import java.util.List;

@lombok.Data
public class Data {
    List<Object> inputRestriction;

    public Data(List<Object> inputRestriction) {
        this.inputRestriction = inputRestriction;
    }
}
