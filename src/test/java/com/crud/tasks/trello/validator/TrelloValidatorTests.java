package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
class TrelloValidatorTests {
    private final TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    void testValidateCard(CapturedOutput output) {
        // Given
        String log = "Someone is testing my application!";
        TrelloCard trelloCard1 = new TrelloCard("testcard1", "description1", "pos", "1");

        // When
        trelloValidator.validateCard(trelloCard1);

        // Then
        assertThat(output.getOut()).contains(log);
    }

    @Test
    void testValidateTrelloBoards(CapturedOutput output) {
        // Given
        String log = "Boards have been filtered. Current list size: 2";
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board1", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "board2", new ArrayList<>());
        List<TrelloBoard> boards = new ArrayList<>(List.of(trelloBoard1, trelloBoard2));

        // When
        trelloValidator.validateTrelloBoards(boards);

        // Then
        assertThat(output.getOut()).contains(log);
    }
}