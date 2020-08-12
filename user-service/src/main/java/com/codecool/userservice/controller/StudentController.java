package com.codecool.userservice.controller;

import com.codecool.userservice.entity.Student;
import com.codecool.userservice.model.UserCredentials;
import com.codecool.userservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/user/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/{username}")
    public Optional<Student> getStudentByUsername(@PathVariable("username") String username) {
        return studentService.getStudentByUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCredentials student) {
        studentService.register(student.getUsername(), student.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(student.getUsername());
    }
}
