package com.meriammejri.jeudecarets.dto;

import com.meriammejri.jeudecarets.utils.Color;
import com.meriammejri.jeudecarets.utils.Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Color color;
    private Value value;
}
