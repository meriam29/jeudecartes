package com.meriammejri.jeudecarets.dto;

import com.meriammejri.jeudecarets.utils.Color;
import com.meriammejri.jeudecarets.utils.Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortHandResponseDto {

    private List<CardDto> sortedCards;
    private List<Color> colorOrder;
    private List<Value> valueOrder;
}
