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

        Card card = Card.builder().question("cica").answer("cat").build();
        Card card2 = Card.builder().question("kutya").answer("dog").build();
        cardRepository.save(card);
        cardRepository.save(card2);
        Deck deck=Deck.builder().name("default").description("default deck").card(card).card(card2).userId(Long.valueOf(1)).build();
        Deck deck2=Deck.builder().name("default2").description("default deck").userId(Long.valueOf(2)).build();
        Deck deck3=Deck.builder().name("default3").description("default deck").userId(Long.valueOf(1)).build();
        deckRepository.save(deck);
        deckRepository.save(deck2);
        deckRepository.save(deck3);
    }

    @Override
    public void run(String... args) throws Exception {
          createCards();
    }
}
