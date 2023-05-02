package com.epde.testifyspring.service;

import com.epde.testifyspring.model.Student;
import com.epde.testifyspring.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentServiceImplIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSave() {
        Student student = new Student("Md. Mosfikur", "Rahman");
        studentService.save(student);
        Student savedStudent = studentRepository.findById(student.getStudentId()).orElse(null);
        assertNotNull(savedStudent);
        assertEquals(student.getFirstName(), savedStudent.getFirstName());
        assertEquals(student.getLastName(), savedStudent.getLastName());
    }

    @Test
    void testFindAll() {
        Student student1 = new Student("Md. Mosfikur", "Rahman");
        Student student2 = new Student("Farzana", "Yesmin");
        studentService.save(student1);
        studentService.save(student2);
        List<Student> students = studentService.findAll();
        assertEquals(2, students.size());
    }

    @Test
    void testDeleteById() {
        Student student = new Student("Md. Mosfikur", "Rahman");
        studentService.save(student);
        studentService.deleteById(student.getStudentId());
        Student deletedStudent = studentRepository.findById(student.getStudentId()).orElse(null);
        assertNull(deletedStudent);
    }

}
