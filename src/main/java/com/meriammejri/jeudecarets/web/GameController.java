package com.meriammejri.jeudecarets.web;

import com.meriammejri.jeudecarets.dto.CardDto;
import com.meriammejri.jeudecarets.dto.SortHandResponseDto;
import com.meriammejri.jeudecarets.exceptions.EmptyCardListException;
import com.meriammejri.jeudecarets.service.CardGame;
import com.meriammejri.jeudecarets.utils.Color;
import com.meriammejri.jeudecarets.utils.Value;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final CardGame cardGame;

    @Operation(summary = "Sort a hand of cards", description = "Sorts a list of cards based on a random order of colors and values.")
    @ApiResponse(responseCode = "200", description = "Successfully sorted the cards with the color and value order.")
    @ApiResponse(responseCode = "400", description = "Invalid input: card list is null or empty.")
    @PostMapping("/sorthand")
    public SortHandResponseDto sortHand(@Valid @RequestBody List<CardDto> cardDtoList) {
        if (cardDtoList == null || cardDtoList.isEmpty()) {
            throw new EmptyCardListException("Card list cannot be empty.");
        }
        // Generate random orders
        List<Color> colorOrder = cardGame.generateRandomColorOrder();
        List<Value> valueOrder = cardGame.generateRandomValueOrder();

        // Sort the hand
        List<CardDto> sortedCards = cardGame.sortHand(cardDtoList, colorOrder, valueOrder);

        // Return the response DTO
        return new SortHandResponseDto(sortedCards, colorOrder, valueOrder);
    }

    @Operation(summary = "Generate a random hand of cards")
    @ApiResponse(responseCode = "200", description = "Successfully generated the hand")
    @ApiResponse(responseCode = "400", description = "Invalid cardNumber parameter")
    @GetMapping("/generate-hand")
    public ResponseEntity<List<CardDto>> generateRandomHand(@RequestParam int cardNumber) {
        // Validate the input
        if (cardNumber < 1 || cardNumber > 52) {
            throw new IllegalArgumentException("cardNumber must be between 1 and 52 inclusive.");
        }

        // Generate the hand
        List<CardDto> randomHand = cardGame.generateRandomHand(cardNumber);

        return new ResponseEntity<>(randomHand, HttpStatus.OK);
    }

}
