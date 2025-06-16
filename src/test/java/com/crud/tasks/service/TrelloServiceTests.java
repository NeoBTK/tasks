package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTests {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    void testFetchTrelloBoards() {
        // Given
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto();
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto();
        List<TrelloBoardDto> trelloBoardDtos = List.of(trelloBoardDto1, trelloBoardDto2);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        // When
        List<TrelloBoardDto> fetchedTrelloBoardDtos = trelloService.fetchTrelloBoards();

        // Then
        assertTrue(fetchedTrelloBoardDtos.containsAll(trelloBoardDtos));
    }

    @Test
    void testCreateTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("cardDto1", "cardDto1description", "pos", "list1");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto("1", trelloCardDto.getName(), "test.com"));

        // When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        // Then
        assertEquals("cardDto1", createdTrelloCardDto.getName());
    }
}