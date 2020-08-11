package com.codecool.deckhandlerservice.entity;

import lombok.AllArgsConstructor;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Card {

    @GeneratedValue
    @Id
    private Long id;
    private String question;
    private String answer;
    private Long deckId;
}
