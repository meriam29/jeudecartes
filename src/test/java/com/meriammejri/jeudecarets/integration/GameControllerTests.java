package com.meriammejri.jeudecarets.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meriammejri.jeudecarets.dto.CardDto;
import com.meriammejri.jeudecarets.service.CardGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GameControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    private CardGame cardGame;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        cardGame = Mockito.mock(CardGame.class); // Create a mock instance of CardGameImpl
    }

    @Test
    void testSortHand_emptyList() throws Exception {
        List<CardDto> inputCards = List.of();

        mockMvc.perform(post("/api/game/sorthand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputCards)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Card list cannot be empty."));
    }

    @Test
    void testSortHand_invalidJson() throws Exception {
        String invalidJson = "{ invalid }";

        mockMvc.perform(post("/api/game/sorthand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSortHand_missingFields() throws Exception {
        String missingFieldsJson = "[ { \"color\": \"COEUR\" } ]";

        mockMvc.perform(post("/api/game/sorthand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(missingFieldsJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGenerateRandomHand_validInput() throws Exception {
        int cardNumber = 10;

        String response = mockMvc.perform(get("/api/game/generate-hand")
                        .param("cardNumber", String.valueOf(cardNumber))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Deserialize response
        List<CardDto> randomHand = objectMapper.readValue(response, new TypeReference<>() {
        });

        assertNotNull(randomHand, "The generated hand should not be null.");
        assertEquals(cardNumber, randomHand.size(), "The size of the hand should match the requested card number.");

        // Ensure all cards are unique
        Set<CardDto> uniqueCards = new HashSet<>(randomHand);
        assertEquals(cardNumber, uniqueCards.size(), "The generated hand should contain unique cards.");
    }

    @Test
    void testGenerateRandomHand_minimumInput() throws Exception {
        int cardNumber = 1;

        String response = mockMvc.perform(get("/api/game/generate-hand")
                        .param("cardNumber", String.valueOf(cardNumber))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Deserialize response
        List<CardDto> randomHand = objectMapper.readValue(response, new TypeReference<>() {
        });

        assertNotNull(randomHand, "The generated hand should not be null.");
        assertEquals(cardNumber, randomHand.size(), "The size of the hand should match the requested card number.");

        // Ensure the single card is unique
        Set<CardDto> uniqueCards = new HashSet<>(randomHand);
        assertEquals(cardNumber, uniqueCards.size(), "The generated hand should contain only one unique card.");
    }

    @Test
    void testGenerateRandomHand_invalidInput_lessThanOne() throws Exception {
        int cardNumber = 0;

        mockMvc.perform(get("/api/game/generate-hand")
                        .param("cardNumber", String.valueOf(cardNumber))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("cardNumber must be between 1 and 52 inclusive."));
    }

    @Test
    void testGenerateRandomHand_invalidInput_moreThanFiftyTwo() throws Exception {
        int cardNumber = 53;

        mockMvc.perform(get("/api/game/generate-hand")
                        .param("cardNumber", String.valueOf(cardNumber))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("cardNumber must be between 1 and 52 inclusive."));
    }

}
