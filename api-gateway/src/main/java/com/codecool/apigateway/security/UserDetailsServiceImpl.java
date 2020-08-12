package com.codecool.apigateway.security;

import com.codecool.apigateway.model.Student;
import com.codecool.apigateway.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentService studentService;

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object.
     * Spring will call this code to retrieve a user upon login from the DB.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentService.findByUsername(username);
        if (student == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(
                student.getUsername(),
                student.getPassword(),
                student.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList())
        );
    }

//    private final RestTemplate template;
//
//    @Value("${user-service.url}")
//    private String baseUrl;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("username", username);
////        Student student = template.exchange(baseUrl+"/"+username, HttpMethod.GET, username, UserDetails.class, uriVariables);
//
//        return new User(
//                student.getUsername(),
//                student.getPassword(),
//                student.getRoles()
//                        .stream()
//                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                        .collect(Collectors.toList())
//        );
//    }

}
