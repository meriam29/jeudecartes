package com.meriammejri.jeudecarets.model;

import com.meriammejri.jeudecarets.utils.Color;
import com.meriammejri.jeudecarets.utils.Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private Color color;
    private Value value;

    @Override
    public String toString() {
        return value + " de " + color;
    }
}

