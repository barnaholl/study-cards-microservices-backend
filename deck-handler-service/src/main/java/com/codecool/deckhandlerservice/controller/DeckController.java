package com.codecool.deckhandlerservice.controller;

import com.codecool.deckhandlerservice.entity.Deck;
import com.codecool.deckhandlerservice.repository.DeckRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
public class DeckController {

    private final DeckRepository deckRepository;

    public DeckController(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @GetMapping("/deck/all")
    public List<Deck> getAllDecks(){
        return deckRepository.findAll();
    }
}
