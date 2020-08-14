package com.codecool.userservice.service;

import com.codecool.userservice.entity.Student;
import com.codecool.userservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByUsername(String username) {
        return studentRepository.findUserByUsername(username);
    }

    public void register(String username, String password) {
        Student newStudent = Student.builder()
                .username(username)
                .password(password)
                .role("ROLE_USER")
                .build();
        studentRepository.save(newStudent);
    }
}
