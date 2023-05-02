package com.epde.testifyspring.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epde.testifyspring.model.Student;
import com.epde.testifyspring.repository.StudentRepository;

public class StudentServiceImplTest {

    private StudentRepository repository;
    private StudentServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(StudentRepository.class);
        service = new StudentServiceImpl(repository);
    }

    @Test
    void testFindAll() {
        List<Student> expected = new ArrayList<>();
        expected.add(new Student("Md. Mosfikur", "Rahman"));
        expected.add(new Student("Farzana", "Yesmin"));
        when(repository.findAll()).thenReturn(expected);
        List<Student> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Student expected = new Student("Md. Mosfikur", "Rahman");
        when(repository.findById(id)).thenReturn(Optional.of(expected));
        Optional<Student> actual = service.findById(id);
        assertEquals(expected, actual.get());
    }

    @Test
    void testSave() {
        Student expected = new Student("Md. Mosfikur", "Rahman");
        when(repository.save(expected)).thenReturn(expected);
        Student actual = service.save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        service.deleteById(id);
        // Verify that the deleteById method of the repository was called with the correct id
        verify(repository).deleteById(id);
    }
}
