package com.codecool.gameservice.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card{
    private Long id;
    private String question;
    private String answer;

}
