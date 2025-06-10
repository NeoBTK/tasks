package com.crud.tasks;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TasksApplicationTests {

	private TrelloMapper trelloMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void testAllMappers() {
		// Given
		TrelloBoard trelloBoard1 = new TrelloBoard("1", "board1", new ArrayList<>());
		TrelloBoard trelloBoard2 = new TrelloBoard("2", "board2", new ArrayList<>());
		List<TrelloBoard> trelloBoardsList = new ArrayList<>();
		trelloBoardsList.add(trelloBoard1);
		trelloBoardsList.add(trelloBoard2);

		TrelloList trelloList1 = new TrelloList("1", "list1", false);
		TrelloList trelloList2 = new TrelloList("2", "list2", false);
		List<TrelloList> trelloListsList = new ArrayList<>();
		trelloListsList.add(trelloList1);
		trelloListsList.add(trelloList2);

		TrelloCard trelloCard = new TrelloCard("card1", "card1desc", "ok", "1");

		TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("11", "boardDto11", new ArrayList<>());
		TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("22", "boardDto22", new ArrayList<>());
		List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
		trelloBoardDtoList.add(trelloBoardDto1);
		trelloBoardDtoList.add(trelloBoardDto2);

		TrelloListDto trelloListDto1 = new TrelloListDto("111", "listDto111", false);
		TrelloListDto trelloListDto2 = new TrelloListDto("222", "listDto222", false);
		List<TrelloListDto> trelloListDtoList = new ArrayList<>();
		trelloListDtoList.add(trelloListDto1);
		trelloListDtoList.add(trelloListDto2);

		TrelloCardDto trelloCardDto = new TrelloCardDto("cardDto22", "cardDto22desc", "ok", "11");

		trelloMapper = new TrelloMapper();

		// When
		List<TrelloBoardDto> mappedTrelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardsList);
		List<TrelloListDto> mappedTrelloListDtoList = trelloMapper.mapToListDto(trelloListsList);
		TrelloCardDto mappedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);
		List<TrelloBoard> mappedTrelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
		List<TrelloList> mappedTrelloListList = trelloMapper.mapToList(trelloListDtoList);
		TrelloCard mappedTrelloCard = trelloMapper.mapToCard(trelloCardDto);

		// Then
		assertEquals(2, mappedTrelloBoardDtoList.size());
		assertEquals("board2", mappedTrelloBoardDtoList.get(1).getName());
		assertEquals(2, mappedTrelloListDtoList.size());
		assertEquals("list1", mappedTrelloListDtoList.get(0).getName());
		assertEquals("card1desc", mappedTrelloCardDto.getDescription());
		assertEquals(2, mappedTrelloBoardList.size());
		assertEquals("boardDto11", mappedTrelloBoardList.get(0).getName());
		assertEquals(2, mappedTrelloListList.size());
		assertEquals("222", mappedTrelloListList.get(1).getId());
		assertEquals("cardDto22", mappedTrelloCard.getName());
	}
}
