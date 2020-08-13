package com.codecool.gameservice.service;

import com.codecool.gameservice.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class DeckProvider {

    private final RestTemplate restTemplate;

    public DeckProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Queue<Card> currentDeck=new LinkedList<>();

    public Card getCurrentDeck() {
        return currentDeck.poll();
    }

    @Value("${deck-handler-url}")
    private String baseUrl;

    public boolean createDeck(String deckId){
        ResponseEntity<LinkedList> response=restTemplate.getForEntity(baseUrl+"/cards/"+deckId, LinkedList.class);
        if(response.getBody()==null||response.getBody().size()==0){
            return false;
        }
        for(int i=0; i<response.getBody().size();i++){
            LinkedHashMap<String,String> map= (LinkedHashMap<String, String>) response.getBody().get(i);
            Card card = Card.builder().id(Long.valueOf(map.get("id"))).question(map.get("question")).answer(map.get("answer")).build();
            currentDeck.add(card);
        }
        return true;

    }

}
