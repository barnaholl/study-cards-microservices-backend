package com.codecool.deckhandlerservice.service;

import com.codecool.deckhandlerservice.entity.Card;
import com.codecool.deckhandlerservice.entity.Deck;
import com.codecool.deckhandlerservice.repository.CardRepository;
import com.codecool.deckhandlerservice.repository.DeckRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;

    public DataInitializer(CardRepository cardRepository, DeckRepository deckRepository) {
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
    }

    public void createCards(){
        Deck deck=Deck.builder().name("default").description("default deck").build();
        deckRepository.save(deck);
        Card card = Card.builder().question("cica").answer("cat").deckId(Long.valueOf(1)).build();
        Card card2 = Card.builder().question("kutya").answer("dog").deckId(Long.valueOf(1)).build();
        cardRepository.save(card);
        cardRepository.save(card2);
    }

    @Override
    public void run(String... args) throws Exception {
          createCards();
    }
}
