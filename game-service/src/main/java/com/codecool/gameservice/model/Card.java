package com.codecool.gameservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Long id;
    private String question;
    private String answer;
}
