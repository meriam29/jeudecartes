package com.meriammejri.jeudecarets.mapper;

import com.meriammejri.jeudecarets.dto.CardDto;
import com.meriammejri.jeudecarets.model.Card;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper used to transform {@link Card} into {@link CardDto} and vice versa.
 */
@Mapper(componentModel = "spring")
public interface CardMapper {

    Card toCardEntity(CardDto cardDto);

    CardDto toCardDto(Card card);

    List<Card> toCardEntityList(List<CardDto> cardDtoList);

    List<CardDto> toCardDtoList(List<Card> cardList);


}
