package com.codecool.deckhandlerservice.repository;

import com.codecool.deckhandlerservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
    Card getById(Long cardId);

    List<Card> getAllById(Long deckId);
}
