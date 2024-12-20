package com.meriammejri.jeudecarets.dto;

import com.meriammejri.jeudecarets.utils.Color;
import com.meriammejri.jeudecarets.utils.Value;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    @NotNull(message = "Color is required.")
    private Color color;

    @NotNull(message = "Value is required.")
    private Value value;
}
