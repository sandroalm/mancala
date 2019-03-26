package com.game.mancala;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.game.mancala.model.Board;
import com.game.mancala.service.MancalaService;

public class MancalaServiceTest {

	private MancalaService mancalaService = new MancalaService();
	private Board board;

	@Before
	public void setUp() {
		board = mancalaService.getBoard();
	}

	@Test
	public void shouldMoveTONExtBoardSameSide() {
		int player = 0;
		int pit = 0;
		mancalaService.move(player, pit);

		List<Integer> pits = board.getSide(player).getPits();
		//
		Assertions.assertThat(pits).containsExactly(0, 7, 7, 7, 7, 7, 1);
	}

	@Test
	public void shouldMoveTONExtBoardOtherSide() {
		int player = 0;
		int pit = 1;
		mancalaService.move(player, pit);

		List<Integer> pits = board.getSide(player).getPits();
		List<Integer> otherSide = board.getSide(1).getPits();
		//
		Assertions.assertThat(pits).containsExactly(6, 0, 7, 7, 7, 7, 1);
		//
		Assertions.assertThat(otherSide).containsExactly(7, 6, 6, 6, 6, 6, 0);
	}

	@Test
	public void shouldNotMoveTONExtBoardBIGPile() {
		// given
		int player = 0;
		int pit = 5;
		int overFlowingNumber = 8;
		board.getSide(player).getPits().set(5, overFlowingNumber);

		mancalaService.move(player, pit);

		List<Integer> pits = board.getSide(player).getPits();

		List<Integer> otherSide = board.getSide(1).getPits();

		Assertions.assertThat(pits).containsExactly(7, 6, 6, 6, 6, 0, 1);
		Assertions.assertThat(otherSide).containsExactly(7, 7, 7, 7, 7, 7, 0);
	}

	@Test
	public void shouldUpdateMoveOpositePitWhenLastIsEmpty() {
		// given
		int player = 0;
		int pit = 1;
		board.getSide(player).getPits().set(pit, 1);
		board.getSide(player).getPits().set(pit + 1, 0);

		// other side
		board.getSide(1).getPits().set(3, 2);

		mancalaService.move(player, pit);

		List<Integer> pits = board.getSide(player).getPits();
		List<Integer> otherSide = board.getSide(1).getPits();

		Assertions.assertThat(pits).containsExactly(6, 0, 0, 6, 6, 6, 3);
		Assertions.assertThat(otherSide).containsExactly(6, 6, 6, 0, 6, 6, 0);
	}
}
