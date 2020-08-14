package com.codecool.userservice.service;

import com.codecool.userservice.entity.Student;
import com.codecool.userservice.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository allStudents;

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(StudentRepository allStudents) {
        this.allStudents = allStudents;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void run(String... args) {

        Student student1 = Student.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .role("ROLE_ADMIN")
                .role("ROLE_USER")
                .build();

        Student student2 = Student.builder()
                .username("student")
                .password(passwordEncoder.encode("student"))
                .role("ROLE_USER")
                .build();

        allStudents.save(student1);
        allStudents.save(student2);
    }
}
