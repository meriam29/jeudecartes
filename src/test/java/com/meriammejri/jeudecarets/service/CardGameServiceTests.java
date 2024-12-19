package com.meriammejri.jeudecarets.service;

import com.meriammejri.jeudecarets.dto.CardDto;
import com.meriammejri.jeudecarets.mapper.CardMapper;
import com.meriammejri.jeudecarets.model.Card;
import com.meriammejri.jeudecarets.service.impl.CardGameImpl;
import com.meriammejri.jeudecarets.utils.Color;
import com.meriammejri.jeudecarets.utils.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

public class CardGameServiceTests {

    @Mock
    private CardMapper cardMapper;

    @InjectMocks
    private CardGameImpl cardGameImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSortHand() {
        // Input data
        List<CardDto> cardDtoList = Arrays.asList(
                new CardDto(Color.TREFLE, Value.DIX),
                new CardDto(Color.COEUR, Value.AS),
                new CardDto(Color.PIQUE, Value.ROI),
                new CardDto(Color.CARREAUX, Value.SEPT),
                new CardDto(Color.TREFLE, Value.CINQ),
                new CardDto(Color.PIQUE, Value.DAME)
        );

        List<Card> hand = Arrays.asList(
                new Card(Color.TREFLE, Value.DIX),
                new Card(Color.COEUR, Value.AS),
                new Card(Color.PIQUE, Value.ROI),
                new Card(Color.CARREAUX, Value.SEPT),
                new Card(Color.TREFLE, Value.CINQ),
                new Card(Color.PIQUE, Value.DAME)
        );

        List<Card> sortedHand = Arrays.asList(
                new Card(Color.COEUR, Value.AS),
                new Card(Color.PIQUE, Value.ROI),
                new Card(Color.PIQUE, Value.DAME),
                new Card(Color.TREFLE, Value.DIX),
                new Card(Color.TREFLE, Value.CINQ),
                new Card(Color.CARREAUX, Value.SEPT)
        );

        List<CardDto> expectedSortedHand = Arrays.asList(
                new CardDto(Color.COEUR, Value.AS),
                new CardDto(Color.PIQUE, Value.ROI),
                new CardDto(Color.PIQUE, Value.DAME),
                new CardDto(Color.TREFLE, Value.DIX),
                new CardDto(Color.TREFLE, Value.CINQ),
                new CardDto(Color.CARREAUX, Value.SEPT)
        );

        // Mock behavior
        when(cardMapper.toCardEntityList(cardDtoList)).thenReturn(hand);
        when(cardMapper.toCardDtoList(sortedHand)).thenReturn(expectedSortedHand);

        // Define orders
        List<Color> colorOrder = Arrays.asList(Color.COEUR, Color.PIQUE, Color.TREFLE, Color.CARREAUX);
        List<Value> valueOrder = Arrays.asList(Value.AS, Value.ROI, Value.DAME, Value.DIX, Value.CINQ, Value.SEPT);

        // Execute method
        List<CardDto> result = cardGameImpl.sortHand(cardDtoList, colorOrder, valueOrder);

        // Verify
        assertEquals(expectedSortedHand, result, "The sorted hand does not match the expected result.");
    }

    @Test
    void testSortHandWithEmptyInput() {
        List<CardDto> cardDtoList = Collections.emptyList();
        List<Color> colorOrder = Arrays.asList(Color.COEUR, Color.PIQUE, Color.TREFLE, Color.CARREAUX);
        List<Value> valueOrder = Arrays.asList(Value.AS, Value.ROI, Value.DAME, Value.DIX, Value.CINQ, Value.SEPT);

        List<CardDto> result = cardGameImpl.sortHand(cardDtoList, colorOrder, valueOrder);

        assertTrue(result.isEmpty(), "The result should be an empty list.");
    }

    @Test
    void testSortHandWithSingleCard() {
        // Input data
        List<CardDto> cardDtoList = List.of(new CardDto(Color.COEUR, Value.AS));
        List<Color> colorOrder = Arrays.asList(Color.COEUR, Color.PIQUE, Color.TREFLE, Color.CARREAUX);
        List<Value> valueOrder = Arrays.asList(Value.AS, Value.ROI, Value.DAME, Value.DIX);

        // Mock behavior
        when(cardMapper.toCardEntityList(cardDtoList))
                .thenReturn(List.of(new Card(Color.COEUR, Value.AS)));
        when(cardMapper.toCardDtoList(anyList()))
                .thenReturn(List.of(new CardDto(Color.COEUR, Value.AS)));

        // Execute method
        List<CardDto> result = cardGameImpl.sortHand(cardDtoList, colorOrder, valueOrder);

        // Verify
        assertEquals(cardDtoList, result, "The result should contain the single input card unchanged.");
    }

    @Test
    void testSortHandWithDuplicateCards() {
        // Input data
        List<CardDto> cardDtoList = Arrays.asList(
                new CardDto(Color.COEUR, Value.AS),
                new CardDto(Color.COEUR, Value.AS),
                new CardDto(Color.PIQUE, Value.DAME),
                new CardDto(Color.PIQUE, Value.DAME)
        );

        // Define order
        List<Color> colorOrder = Arrays.asList(Color.COEUR, Color.PIQUE, Color.TREFLE, Color.CARREAUX);
        List<Value> valueOrder = Arrays.asList(Value.AS, Value.DAME, Value.ROI, Value.DIX);

        // Mock CardMapper behavior
        when(cardMapper.toCardEntityList(cardDtoList))
                .thenReturn(Arrays.asList(
                        new Card(Color.COEUR, Value.AS),
                        new Card(Color.COEUR, Value.AS),
                        new Card(Color.PIQUE, Value.DAME),
                        new Card(Color.PIQUE, Value.DAME)
                ));

        when(cardMapper.toCardDtoList(anyList()))
                .thenReturn(Arrays.asList(
                        new CardDto(Color.COEUR, Value.AS),
                        new CardDto(Color.COEUR, Value.AS),
                        new CardDto(Color.PIQUE, Value.DAME),
                        new CardDto(Color.PIQUE, Value.DAME)
                ));

        // Expected result
        List<CardDto> expectedSortedHand = Arrays.asList(
                new CardDto(Color.COEUR, Value.AS),
                new CardDto(Color.COEUR, Value.AS),
                new CardDto(Color.PIQUE, Value.DAME),
                new CardDto(Color.PIQUE, Value.DAME)
        );

        // Execute method
        List<CardDto> result = cardGameImpl.sortHand(cardDtoList, colorOrder, valueOrder);

        // Verify
        assertEquals(expectedSortedHand, result, "The result should correctly handle duplicate cards.");
    }

    @Test
    void testGenerateRandomHand_invalidInput_lessThanOne() {
        int cardNumber = 0;

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cardGameImpl.generateRandomHand(cardNumber),
                "Expected IllegalArgumentException for input less than 1"
        );

        assertEquals("cardNumber must be between 1 and 52 inclusive.", exception.getMessage());
    }

    @Test
    void testGenerateRandomHand_invalidInput_moreThanFiftyTwo() {
        int cardNumber = 53;

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cardGameImpl.generateRandomHand(cardNumber),
                "Expected IllegalArgumentException for input greater than 52"
        );

        assertEquals("cardNumber must be between 1 and 52 inclusive.", exception.getMessage());
    }
}
