package com.codecool.deckhandlerservice.repository;

import com.codecool.deckhandlerservice.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck,Long> {
}
