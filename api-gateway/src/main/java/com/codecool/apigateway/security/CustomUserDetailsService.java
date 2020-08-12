package com.codecool.apigateway.security;

import com.codecool.apigateway.model.Student;
import com.codecool.apigateway.service.StudentService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private StudentService studentService;
//
//    public CustomUserDetailsService(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    /**
//     * Loads the user from the DB and converts it to Spring Security's internal User object.
//     * Spring will call this code to retrieve a user upon login from the DB.
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        //TODO
//        Student student = studentService.findUserByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
//
//        //TODO
//        return new User(student.getUsername(), student.getPassword(),
//                student.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//    }
//}
