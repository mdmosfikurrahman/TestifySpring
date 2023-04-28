package com.epde.testifyspring.controller;

import com.epde.testifyspring.model.Student;
import com.epde.testifyspring.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    List<Student> getAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{studentId}")
    Optional<Student> getById(@PathVariable Long studentId) {
        return service.findById(studentId);
    }

    @PostMapping
    Student add(@RequestBody Student student) {
        return service.save(student);
    }

    @PutMapping
    Student update(@RequestBody Student student) {
        return service.save(student);
    }

    @DeleteMapping(value = "/{studentId}")
    void delete(@PathVariable Long studentId) {
        service.deleteById(studentId);
    }
}
