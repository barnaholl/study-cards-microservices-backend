package com.codecool.deckhandlerservice.repository;

import com.codecool.deckhandlerservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
}
