package com.codecool.deckhandlerservice.controller;

import com.codecool.deckhandlerservice.entity.Card;
import com.codecool.deckhandlerservice.entity.Deck;
import com.codecool.deckhandlerservice.entity.ExportCard;
import com.codecool.deckhandlerservice.repository.CardRepository;
import com.codecool.deckhandlerservice.repository.DeckRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class DeckController {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

    public DeckController(DeckRepository deckRepository, CardRepository cardRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    @GetMapping("/decks")
    public List<Deck> getAllDecks(){
        return deckRepository.findAll();
    }

    @GetMapping("/decks/{userId}")
    public List<Deck> getAllDecksByUserId(@PathVariable("userId") Long userId){
        return deckRepository.findAllByUserId(userId);
    }
    @GetMapping("/cards/{deckId}")
    public List<ExportCard> getCardsByDeckId(@PathVariable("deckId") Long deckId){
        Deck deck = deckRepository.getById(deckId);
        List<Card> cardList=deck.getCards();
        List<ExportCard> exportCardList=new ArrayList<>();
        for(Card card: cardList){
           ExportCard exportCard= ExportCard.builder().id(String.valueOf(card.getId())).question(card.getQuestion()).answer(card.getAnswer()).build();
           exportCardList.add(exportCard);
        }
        return exportCardList;
    }

    @GetMapping("/deck/{id}")
    public Deck getDeckById(@PathVariable("id") Long id){
        return deckRepository.getById(id);
    }

    @PostMapping("/deck")
    public void createDeckByUserId(@RequestBody Deck deck){
        //body needs to contain:Name,Desc,UserId
        deckRepository.save(deck);
    }

    @PostMapping("/card/{deckId}")
    public void addCardToDeck(@RequestBody Card card,@PathVariable("deckId") Long deckId){
        //body needs to contain:Question,Answer
        cardRepository.save(card);
        Deck deck=deckRepository.getById(deckId);
        List<Card> cards=deck.getCards();
        cards.add(card);
        deck.setCards(cards);
        deckRepository.save(deck);
    }

    @DeleteMapping("/card/{deckId}/{cardId}")
    public void deleteCard(@PathVariable("deckId") Long deckId, @PathVariable("cardId") Long cardId){
        Deck deck=deckRepository.getById(deckId);
        List<Card> cards=deck.getCards();
        Card cardToRemove=cardRepository.getById(cardId);
        cards.remove(cardToRemove);
        deck.setCards(cards);
        deckRepository.save(deck);
        cardRepository.deleteById(cardId);
    }

    @DeleteMapping("/deck/{deckId}")
    public void deleteDeck(@PathVariable("deckId") Long deckId){
        deckRepository.deleteById(deckId);
    }




}
