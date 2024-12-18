package com.meriammejri.jeudecarets.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hand {
    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public void printHand() {
        cards.forEach(System.out::println);
    }
}