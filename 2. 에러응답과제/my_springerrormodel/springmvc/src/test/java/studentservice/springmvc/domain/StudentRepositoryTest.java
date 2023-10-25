package studentservice.springmvc.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import studentservice.springmvc.model.student.Student;
import studentservice.springmvc.model.student.StudentRepository;

class StudentRepositoryTest {
    StudentRepository studentRepository = new StudentRepository();

    @AfterEach
    void afterEach() {
        studentRepository.clearStore();
    }

    @Test
    void AddStudent(){
        //given
        Student student1 = new Student("lee", 2);

        //when
        Student Addstudent = studentRepository.addStudent(student1);

        //then
        Assertions.assertThat(Addstudent.getGrade()).isEqualTo(student1.getGrade());
        Assertions.assertThat(Addstudent.getName()).isEqualTo(student1.getName());
    }
}