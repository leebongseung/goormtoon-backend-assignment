package studentservice.springmvc.model.student;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


/*
*   성적으로 -> 성적 조회
*
* */
@Getter
@Setter
@RequiredArgsConstructor
public class Student {
    private String name;
    private int grade;
}
