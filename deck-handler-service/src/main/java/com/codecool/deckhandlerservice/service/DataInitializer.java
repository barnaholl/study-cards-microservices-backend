package com.codecool.deckhandlerservice.service;

import com.codecool.deckhandlerservice.entity.Card;
import com.codecool.deckhandlerservice.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CardRepository cardRepository;

    public DataInitializer(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void createCards(){
        Card card = Card.builder().question("cica").answer("cat").build();
        Card card2 = Card.builder().question("kutya").answer("dog").build();
        cardRepository.save(card);
        cardRepository.save(card2);

    }

    @Override
    public void run(String... args) throws Exception {
          createCards();
    }
}
