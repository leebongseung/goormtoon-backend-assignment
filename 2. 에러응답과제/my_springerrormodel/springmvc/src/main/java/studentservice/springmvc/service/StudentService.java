package studentservice.springmvc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import studentservice.springmvc.exception.ErrorCode;
import studentservice.springmvc.model.communication.ApiResponse;
import studentservice.springmvc.model.communication.Metadata;
import studentservice.springmvc.model.communication.Status;
import studentservice.springmvc.model.student.Student;
import studentservice.springmvc.model.student.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    @ResponseBody

    public Student add(Student student) {
        // 생성자 주입 해야거같은데.. 음...
        Student addStudent = studentRepository.addStudent(student);
        return addStudent;
    }


    public ArrayList<Student> findAll() {
        ArrayList<Student> allStudents = studentRepository.findAll();
        return allStudents;
    }

    public List<Student> getGradeStudent(int grade){
        return studentRepository.get(grade);
    }
}
