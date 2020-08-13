package com.codecool.gameservice.controller;

import com.codecool.gameservice.model.Card;
import com.codecool.gameservice.service.DeckProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

@RestController
@CrossOrigin
public class GameController {

    private final DeckProvider deckProvider;

    public GameController(DeckProvider deckProvider) {
        this.deckProvider = deckProvider;
    }

    @PostMapping("/play/{deckId}")
    public boolean createGame(@PathVariable("deckId") String deckId){
        return deckProvider.createDeck(deckId);
    }

    @GetMapping("/play")
    public Card gerCurrentDeck(){
        return deckProvider.getCurrentDeck();
    }
}
