package com.codecool.gameservice.service;

import com.codecool.gameservice.model.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Queue;

public class DeckProvider {

    private final RestTemplate restTemplate;

    public DeckProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${deck-handler-url}")
    private String baseUrl;

    public Queue<Card> getDeck(String id){
        return restTemplate.getForObject(baseUrl+"/cards/"+id, Queue.class);
    }
}
