package com.codecool.apigateway.controller;

import com.codecool.apigateway.model.UserCredentials;
import com.codecool.apigateway.security.DataValidator;
import com.codecool.apigateway.security.JwtTokenServices;
import com.codecool.apigateway.security.UserDetailsServiceImpl;
import com.codecool.apigateway.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    private final DataValidator dataValidator;

    private final PasswordEncoder passwordEncoder;

    private final StudentService studentService;

    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, DataValidator dataValidator, StudentService studentService, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
        this.dataValidator = dataValidator;
        this.studentService = studentService;
        this.userDetailsService = userDetailsService;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserCredentials userCredentials) {
        log.info(userCredentials.getPassword());
        log.info(userCredentials.getUsername());
        Map<Object, Object> model = new HashMap<>();
        try {
            String username = userCredentials.getUsername();
            String password = userCredentials.getPassword();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            model.put("correct", true);
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            model.put("correct", false);
            model.put("msg", "Wrong username/password!");
            return ResponseEntity.ok(model);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserCredentials userCredentials) {
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();

        Map<Object, Object> model = new HashMap<>();
        List<String> errorList = new ArrayList<>();

        if (studentService.findByUsername(username) != null){
            model.put("correct", false);
            model.put("msg", "Username already exists! Please choose a different username!");
            return ResponseEntity.ok(model);
        }

        if(!dataValidator.isValidUsername(username, errorList)) {
            String error = String.join(" , ", errorList);
            String errorMessage = "Username should have " + error + "!";
            model.put("correct", false);
            model.put("msg", errorMessage);
            return ResponseEntity.ok(model);
        }

        if (!dataValidator.isValidPassword(password, errorList)) {
            String error = String.join(" , ", errorList);
            String errorMessage = "Password should contain " + error + "!";
            model.put("correct", false);
            model.put("msg", errorMessage);
            return ResponseEntity.ok(model);
        }

        UserCredentials newUserCredentials = UserCredentials.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        studentService.registerNewStudent(newUserCredentials);

        List<String> roles = Collections.singletonList("ROLE_USER");
        String token = jwtTokenServices.createToken(username, roles);
        model.put("correct", true);
        model.put("username", username);
        model.put("roles", roles);
        model.put("token", token);
        return ResponseEntity.ok(model);
    }

}
