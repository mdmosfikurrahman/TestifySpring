package com.epde.testifyspring.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testStudentConstructorAndGetters() {
        // Arrange
        String firstName = "FIRST_NAME";
        String lastName = "LAST_NAME";

        // Act
        Student student = new Student(firstName, lastName);

        // Assert
        assertEquals(firstName, student.getFirstName());
        assertEquals(lastName, student.getLastName());
    }

    @Test
    public void testStudentSetters() {
        // Arrange
        Student student = new Student("Md. Mosfikur", "Rahman");
        String newFirstName = "Farzana";
        String newLastName = "Yesmin";

        // Act
        student.setFirstName(newFirstName);
        student.setLastName(newLastName);

        // Assert
        assertEquals(newFirstName, student.getFirstName());
        assertEquals(newLastName, student.getLastName());
    }
}