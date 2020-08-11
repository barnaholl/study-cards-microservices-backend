package com.codecool.userservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="students")
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

}
