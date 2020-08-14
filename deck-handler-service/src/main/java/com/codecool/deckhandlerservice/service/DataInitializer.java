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

    public void createCards() {

        Card card = Card.builder().question("Hungary").answer("Budapest").build();
        Card card2 = Card.builder().question("USA").answer("Washington").build();
        Card card3 = Card.builder().question("England").answer("London").build();
        Card card4 = Card.builder().question("Austria").answer("Vienna").build();

        Card card5 = Card.builder().question("kutya").answer("dog").build();
        Card card6 = Card.builder().question("macska").answer("cat").build();
        Card card7 = Card.builder().question("őz").answer("deer").build();
        Card card8 = Card.builder().question("kígyó").answer("snake").build();
        Card card9 = Card.builder().question("oroszlán").answer("lion").build();
        Card card10 = Card.builder().question("medve").answer("bear").build();

        Card card11 = Card.builder().question("Alfa Romeo").answer("Italy").build();
        Card card12 = Card.builder().question("Volvo").answer("Sweden").build();
        Card card13 = Card.builder().question("Peugeot").answer("France").build();
        Card card14 = Card.builder().question("Mercedes").answer("Germany").build();
        Card card15 = Card.builder().question("Tesla").answer("USA").build();

        cardRepository.save(card);
        cardRepository.save(card2);
        cardRepository.save(card3);
        cardRepository.save(card4);
        cardRepository.save(card5);
        cardRepository.save(card6);
        cardRepository.save(card7);
        cardRepository.save(card8);
        cardRepository.save(card9);
        cardRepository.save(card10);
        cardRepository.save(card11);
        cardRepository.save(card12);
        cardRepository.save(card13);
        cardRepository.save(card14);
        cardRepository.save(card15);

        Deck deck = Deck.builder().name("capitals").description("Capital of countries")
                .card(card)
                .card(card2)
                .card(card3)
                .card(card4)
                .userId(Long.valueOf(1)).build();
        Deck deck2 = Deck.builder().name("animals").description("Learning animals is fun!")
                .card(card5)
                .card(card6)
                .card(card7)
                .card(card8)
                .card(card9)
                .card(card10)
                .userId(Long.valueOf(2)).build();
        Deck deck3 = Deck.builder().name("cars").description("Cars and countries")
                .card(card11)
                .card(card12)
                .card(card13)
                .card(card14)
                .card(card15)
                .userId(Long.valueOf(1)).build();
        deckRepository.save(deck);
        deckRepository.save(deck2);
        deckRepository.save(deck3);
    }

    @Override
    public void run(String... args) throws Exception {
        createCards();
    }
}
