package com.meriammejri.jeudecarets.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Hand {
    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void printHand() {
        cards.forEach(card -> log.info(String.valueOf(card)));
    }
}