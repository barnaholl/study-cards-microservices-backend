package com.codecool.deckhandlerservice.repository;

import com.codecool.deckhandlerservice.entity.Card;
import com.codecool.deckhandlerservice.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck,Long> {
    List<Deck> findAllByUserId(Long id);

    Deck getById(Long id);

}
