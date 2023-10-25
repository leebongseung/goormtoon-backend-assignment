package studentservice.springmvc.model.student;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.beans.PropertyVetoException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/*  성적으로 -> 이름 조회
*   성적 -> 오름차순 출력
*
* */

@Slf4j
@Repository
public class StudentRepository {
    private Long sequence = 0L;
    private static Map<Long, Student> studentRepository = new ConcurrentHashMap<>();

    public Student addStudent(Student student){
        log.info("student is id={}, Name={}, Grade={}",sequence, student.getName(), student.getGrade());
        studentRepository.put(sequence++, student);

        return student;
    }

    public int getSize(){
        return studentRepository.size();
    }
    public ArrayList<Student> findAll(){
        ArrayList<Student> allStudents = new ArrayList<>();
        allStudents.addAll(studentRepository.values());
        return allStudents;
    }

    public List<Student> get(int grade){
        return studentRepository.values().stream()
                .filter(student -> student.getGrade() == grade)
                .collect(Collectors.toList());
    }


    public void clearStore(){
        studentRepository.clear();
    }
}
