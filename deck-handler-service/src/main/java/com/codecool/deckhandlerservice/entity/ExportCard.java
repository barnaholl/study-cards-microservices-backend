package com.codecool.deckhandlerservice.entity;

import lombok.AllArgsConstructor;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExportCard {

    private String id;
    private String question;
    private String answer;
}
