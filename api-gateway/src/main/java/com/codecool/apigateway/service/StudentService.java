package com.codecool.apigateway.service;

import com.codecool.apigateway.model.Student;
import com.codecool.apigateway.model.UserCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {

    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String baseUrl;

    public StudentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Student findByUsername(String username) {
        return restTemplate.getForEntity(baseUrl + username, Student.class).getBody();
    }

    public void registerNewStudent(UserCredentials student) {
        restTemplate.postForEntity(baseUrl + "register", student, String.class);
    }
}
