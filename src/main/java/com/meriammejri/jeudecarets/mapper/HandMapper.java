package com.meriammejri.jeudecarets.mapper;

import com.meriammejri.jeudecarets.dto.HandDto;
import com.meriammejri.jeudecarets.model.Hand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Mapper used to transform {@link Hand} into {@link HandDto} and vice versa.
 */
@Mapper(componentModel = "spring", uses = CardMapper.class)
public interface HandMapper {

    final CardMapper cardMapper = Mappers.getMapper(CardMapper.class);

    Hand toHandEntity(HandDto handDto);

    List<Hand> toHandEntityList(List<HandDto> handDtoList);

    HandDto toHandDto(Hand handD);

    List<HandDto> toHandDtoList(List<Hand> handList);
}
