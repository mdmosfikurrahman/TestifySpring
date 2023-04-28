package com.epde.testifyspring.service;

import com.epde.testifyspring.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Optional<Student> findById(Long studentId);

    Student save(Student student);

    void deleteById(Long studentId);
}
