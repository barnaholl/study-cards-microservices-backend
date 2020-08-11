package com.codecool.deckhandlerservice.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Deck {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Long userId;
}
