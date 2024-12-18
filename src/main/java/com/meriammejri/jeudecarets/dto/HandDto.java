package com.meriammejri.jeudecarets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandDto {
    private List<CardDto> cards = new ArrayList<>();

}
