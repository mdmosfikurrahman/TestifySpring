package com.epde.testifyspring.service;

import com.epde.testifyspring.model.Student;
import com.epde.testifyspring.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Student> findById(Long studentId) {
        return repository.findById(studentId);
    }

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public void deleteById(Long studentId) {
        repository.deleteById(studentId);
    }
}
