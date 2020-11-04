package com.codecool.userservice.repository;

import com.codecool.userservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findUserByUsername(String username);
}
