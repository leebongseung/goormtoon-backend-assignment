package studentservice.springmvc.model.communication;

import lombok.Data;

@Data
public class InputRestriction {
    private int maxGrade;

    public InputRestriction(int maxGrade) {
        this.maxGrade = maxGrade;
    }
}
