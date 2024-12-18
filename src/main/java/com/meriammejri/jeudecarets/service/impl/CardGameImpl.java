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
}
