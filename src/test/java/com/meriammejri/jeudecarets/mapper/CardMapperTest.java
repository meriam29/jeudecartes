package com.meriammejri.jeudecarets.mapper;

import com.meriammejri.jeudecarets.dto.CardDto;
import com.meriammejri.jeudecarets.model.Card;
import com.meriammejri.jeudecarets.utils.Color;
import com.meriammejri.jeudecarets.utils.Value;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardMapperTest {

    private final CardMapper cardMapper = Mappers.getMapper(CardMapper.class);

    @Test
    void testToCardEntity() {
        CardDto cardDto = new CardDto();
        cardDto.setColor(Color.COEUR);
        cardDto.setValue(Value.CINQ);

        Card card = cardMapper.toCardEntity(cardDto);

        assertEquals(Color.COEUR, card.getColor());
        assertEquals(Value.CINQ, card.getValue());
    }

    @Test
    void testToCardEntityList() {
        CardDto cardDto1 = new CardDto();
        cardDto1.setColor(Color.COEUR);
        cardDto1.setValue(Value.CINQ);

        CardDto cardDto2 = new CardDto();
        cardDto2.setColor(Color.CARREAUX);
        cardDto2.setValue(Value.DAME);

        List<CardDto> cardDtoList = Arrays.asList(cardDto1, cardDto2);
        List<Card> cardList = cardMapper.toCardEntityList(cardDtoList);

        assertEquals(2, cardList.size());
        assertEquals(Color.COEUR, cardList.get(0).getColor());
        assertEquals(Value.CINQ, cardList.get(0).getValue());
        assertEquals(Color.CARREAUX, cardList.get(1).getColor());
        assertEquals(Value.DAME, cardList.get(1).getValue());
    }
}