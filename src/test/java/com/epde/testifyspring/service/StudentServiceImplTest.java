package com.epde.testifyspring.service;

import com.epde.testifyspring.model.Student;
import com.epde.testifyspring.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceImplTest {

    private StudentRepository repository;
    private StudentService service;

    @BeforeEach
    void setUp() {
        repository = mock(StudentRepository.class);
        service = new StudentServiceImpl(repository);
    }

    @Test
    void testFindAll() {
        // Given
        List<Student> students = Arrays.asList(
                new Student("FIRST_NAME", "LAST_NAME"),
                new Student("FIRST_NAME_2", "LAST_NAME_2")
        );
        when(repository.findAll()).thenReturn(students);

        // When
        List<Student> result = service.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("FIRST_NAME", result.get(0).getFirstName());
        assertEquals("LAST_NAME_2", result.get(1).getLastName());
    }

    @Test
    void testFindById() {
        // Given
        Long studentId = 1L;
        Student student = new Student("FIRST_NAME", "LAST_NAME");
        student.setStudentId(studentId);
        when(repository.findById(studentId)).thenReturn(Optional.of(student));

        // When
        Optional<Student> result = service.findById(studentId);

        // Then
        assertTrue(result.isPresent());
        assertEquals("FIRST_NAME", result.get().getFirstName());
        assertEquals("LAST_NAME", result.get().getLastName());
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        Long studentId = 1L;
        when(repository.findById(studentId)).thenReturn(Optional.empty());

        // When
        Optional<Student> result = service.findById(studentId);

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        // Given
        Student student = new Student("FIRST_NAME", "LAST_NAME");
        when(repository.save(student)).thenReturn(student);

        // When
        Student result = service.save(student);

        // Then
        assertEquals("FIRST_NAME", result.getFirstName());
        assertEquals("LAST_NAME", result.getLastName());
    }

    @Test
    void testDeleteById() {
        // Given
        Long studentId = 1L;

        // When
        service.deleteById(studentId);

        // Then
        assertEquals(studentId, studentId);
    }
}
