package studentservice.springmvc.contoller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import studentservice.springmvc.exception.CustomException;
import studentservice.springmvc.model.communication.*;
import studentservice.springmvc.model.student.Student;
import studentservice.springmvc.model.student.StudentRepository;
import studentservice.springmvc.exception.ErrorCode;
import studentservice.springmvc.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@Controller
@RequestMapping("/student")
@AllArgsConstructor
public class addUserController {
    public static final int MAX_GRADE = 6;
    private StudentRepository studentRepository;
    private StudentService studentService;

    @ResponseBody
    @PostMapping("/add")
    public ApiResponse<Student> addStudent(@ModelAttribute Student student) {
        if (student.getGrade() > MAX_GRADE){
            throw new CustomException(ErrorCode.NOT_FOUND, new InputRestriction(MAX_GRADE));
        }
        Student addStudent = studentService.add(student);

        return makeResponse(student);
//        todo: makeResponse 호출 잘해보세요~
    }

    @ResponseBody
    @PostMapping("/findall")
    public ApiResponse<Student> searchAllStudent() {
        ArrayList<Student> students = studentService.findAll();
        return makeResponse(students);

    }

    @ResponseBody
    @GetMapping("/{grade}")
    public ApiResponse getGradeStudent(@PathVariable int grade) {
        return makeResponse(studentService.getGradeStudent(grade));
    }


    public<T> ApiResponse<T> makeResponse(List<T> results){
        return new ApiResponse<>(new Metadata(results.size()), results);
    }

    public<T> ApiResponse<T> makeResponse(T result){
        return makeResponse(Collections.singletonList(result));
    }


//    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ApiResponse customExceptionHandler(HttpServletResponse response, CustomException customException){
        response.setStatus(HttpStatus.OK.value());
        System.out.println(" 여기 실행 되니?");
        return new ApiResponse(new Status(customException.getCode(), customException.getMessage()),
                new Data(Collections.singletonList(customException.getData())));

    }
}
