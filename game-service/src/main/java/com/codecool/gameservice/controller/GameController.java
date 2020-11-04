package com.codecool.gameservice.controller;

import com.codecool.gameservice.model.Card;
import com.codecool.gameservice.service.DeckProvider;
import org.springframework.web.bind.annotation.*;


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
    public Card getCurrentDeck(){
        return deckProvider.getNextCard();
    }

    @PostMapping("/response/{response}")
    public Card postResponse(@PathVariable("response") boolean response){
        if(response){
            deckProvider.removeFromQueue();
            return deckProvider.getNextCard();
        }
        deckProvider.addToQueue();
        return deckProvider.getNextCard();
    }

}
