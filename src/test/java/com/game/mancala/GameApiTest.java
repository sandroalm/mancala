package com.game.mancala;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.mancala.api.GameApi;
import com.game.mancala.model.Board;
import com.game.mancala.service.MancalaService;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GameApi.class)
public class GameApiTest {

	private static final String API_GAME = "/game/board";
	private Board board;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MancalaService mancalaService;

	@Before
	public void before() {
		// given
		board = new Board();
	}

	@Test
	public void shouldGetBoard() throws Exception {
		// when
		MockHttpServletRequestBuilder getOperation = get(API_GAME);

		Mockito.when(mancalaService.getBoard()).thenReturn(board);
		// then
		mvc.perform(getOperation).andExpect(status().isOk())
				.andExpect(jsonPath("$.whosTurn", equalTo(board.getWhosTurn())));
	}

	@Test
	public void shouldMoveBoard() throws Exception {
		// when
		MockHttpServletRequestBuilder post = post(API_GAME).param("player", "1").param("pit", "1");

		Mockito.when(mancalaService.move(1, 1)).thenReturn(board);
		// then
		mvc.perform(post).andExpect(status().isOk()).andExpect(jsonPath("$.whosTurn", equalTo(board.getWhosTurn())));
	}
	
	@Test
	public void shouldResetBoard() throws Exception {
		// when
		MockHttpServletRequestBuilder delete = delete(API_GAME);

		Mockito.when(mancalaService.reset()).thenReturn(board);
		// then
		mvc.perform(delete).andExpect(status().isOk())
				.andExpect(jsonPath("$.whosTurn", equalTo(board.getWhosTurn())));
	}

}
