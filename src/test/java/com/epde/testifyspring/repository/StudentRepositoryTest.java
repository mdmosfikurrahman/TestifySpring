package com.epde.testifyspring.repository;

import com.epde.testifyspring.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTest {

    @Mock
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("FIRST_NAME", "LAST_NAME");
    }

    @Test
    void shouldReturnAllStudents() {
        // Given
        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student));

        // When
        List<Student> students = studentRepository.findAll();

        // Then
        Assertions.assertEquals(1, students.size());
        Assertions.assertEquals(student.getFirstName(), students.get(0).getFirstName());
        Assertions.assertEquals(student.getLastName(), students.get(0).getLastName());
    }

    @Test
    void shouldReturnStudentById() {
        // Given
        Mockito.when(studentRepository.findById(student.getStudentId())).thenReturn(Optional.of(student));

        // When
        Optional<Student> result = studentRepository.findById(student.getStudentId());

        // Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(student.getFirstName(), result.get().getFirstName());
        Assertions.assertEquals(student.getLastName(), result.get().getLastName());
    }

    @Test
    void shouldSaveStudent() {
        // Given
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        // When
        Student savedStudent = studentRepository.save(student);

        // Then
        Assertions.assertEquals(student.getFirstName(), savedStudent.getFirstName());
        Assertions.assertEquals(student.getLastName(), savedStudent.getLastName());
    }

    @Test
    void shouldDeleteStudentById() {
        // When
        studentRepository.deleteById(student.getStudentId());

        // Then
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(student.getStudentId());
    }
}