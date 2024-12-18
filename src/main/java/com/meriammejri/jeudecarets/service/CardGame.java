package com.meriammejri.jeudecarets.service;

import com.meriammejri.jeudecarets.dto.CardDto;
import com.meriammejri.jeudecarets.utils.Color;
import com.meriammejri.jeudecarets.utils.Value;

import java.util.List;

public interface CardGame {

    /**
     * Sort the hand based on custom order
     *
     * @param cardDtoList list of Card
     * @param colorOrder  ordered color
     * @param valueOrder  ordered value
     * @return List<CardDto>
     */

    List<CardDto> sortHand(List<CardDto> cardDtoList, List<Color> colorOrder, List<Value> valueOrder);

    /**
     * Generate a random order for colors
     *
     * @return List<Color>
     */
    List<Color> generateRandomColorOrder();

    /**
     * Generate a random order for values
     *
     * @return List<Value>
     */
    List<Value> generateRandomValueOrder();
}
