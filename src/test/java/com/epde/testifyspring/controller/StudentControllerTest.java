package com.epde.testifyspring.controller;

import com.epde.testifyspring.model.Student;
import com.epde.testifyspring.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {
    private MockMvc mockMvc;

    @Mock
    private StudentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new StudentController(service)).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Md. Mosfikur", "Rahman"));
        students.add(new Student("Farzana", "Yesmin"));
        when(service.findAll()).thenReturn(students);
        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetById() throws Exception {
        Student student = new Student("Md. Mosfikur", "Rahman");
        student.setStudentId(1L);
        when(service.findById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(get("/api/v1/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Md. Mosfikur"))
                .andExpect(jsonPath("$.lastName").value("Rahman"));
    }

    @Test
    void testAdd() throws Exception {
        Student student = new Student("Md. Mosfikur", "Rahman");
        when(service.save(any(Student.class))).thenReturn(student);
        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Md. Mosfikur\", \"lastName\": \"Rahman\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Md. Mosfikur"))
                .andExpect(jsonPath("$.lastName").value("Rahman"));
    }

    @Test
    void testUpdate() throws Exception {
        Student student = new Student("Md. Mosfikur", "Rahman");
        student.setStudentId(1L);
        when(service.save(any(Student.class))).thenReturn(student);
        mockMvc.perform(put("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"studentId\": 1, \"firstName\": \"Md. Mosfikur\", \"lastName\": \"Rahman\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Md. Mosfikur"))
                .andExpect(jsonPath("$.lastName").value("Rahman"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/students/1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteById(1L);
    }
}
