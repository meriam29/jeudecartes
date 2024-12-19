package com.meriammejri.jeudecarets.service.impl;

import com.meriammejri.jeudecarets.dto.CardDto;
import com.meriammejri.jeudecarets.mapper.CardMapper;
import com.meriammejri.jeudecarets.model.Card;
import com.meriammejri.jeudecarets.service.CardGame;
import com.meriammejri.jeudecarets.utils.Color;
import com.meriammejri.jeudecarets.utils.Value;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class CardGameImpl implements CardGame {

    private final CardMapper cardMapper;
    private final Random random = new Random();

    /**
     * Sort the hand based on custom order
     *
     * @param cardDtoList list of Card
     * @param colorOrder  ordered color
     * @param valueOrder  ordered value
     * @return List<CardDto>
     */
    @Override
    public List<CardDto> sortHand(List<CardDto> cardDtoList, List<Color> colorOrder, List<Value> valueOrder) {
        log.info("Start sorting hand");
        Map<Color, Integer> colorPriority = new EnumMap<>(Color.class);
        Map<Value, Integer> valuePriority = new EnumMap<>(Value.class);

        // Assign priorities based on the random order
        for (int i = 0; i < colorOrder.size(); i++) {
            colorPriority.put(colorOrder.get(i), i);
        }
        for (int i = 0; i < valueOrder.size(); i++) {
            valuePriority.put(valueOrder.get(i), i);
        }
        List<Card> hand = cardMapper.toCardEntityList(cardDtoList);
        // Sort the hand
        List<Card> sortedCard = hand.stream()
                .sorted(Comparator
                        .comparing((Card card) -> colorPriority.get(card.getColor()))
                        .thenComparing(card -> valuePriority.get(card.getValue())))
                .toList();

        return cardMapper.toCardDtoList(sortedCard);
    }

    /**
     * Generate a random order for colors
     *
     * @return List<Color>
     */
    @Override
    public List<Color> generateRandomColorOrder() {
        List<Color> colors = Arrays.asList(Color.values());
        Collections.shuffle(colors);
        return colors;
    }

    /**
     * Generate a random order for values
     *
     * @return List<Value>
     */
    @Override
    public List<Value> generateRandomValueOrder() {
        List<Value> values = Arrays.asList(Value.values());
        Collections.shuffle(values);
        return values;
    }

    /**
     * Generate a random hand of cards
     *
     * @param cardNumber
     * @return
     */
    @Override
    public List<CardDto> generateRandomHand(int cardNumber) {
        // Validate input
        if (cardNumber < 1 || cardNumber > 52) {
            throw new IllegalArgumentException("cardNumber must be between 1 and 52 inclusive.");
        }
        // Create and shuffle the full cards
        List<Card> fullCards = generateFullCards();
        Collections.shuffle(fullCards);

        // Pick the first cardNumber cards and map to DTO
        return cardMapper.toCardDtoList(fullCards.subList(0, cardNumber));
    }

    private List<Card> generateFullCards() {

        List<Card> fullCards = new ArrayList<>(52);
        for (Color color : Color.values()) {
            for (Value value : Value.values()) {
                fullCards.add(new Card(color, value));
            }
        }
        return fullCards;
    }
}
