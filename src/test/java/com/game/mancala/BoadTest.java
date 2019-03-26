package com.game.mancala;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.game.mancala.model.Board;
import com.game.mancala.model.Side;

public class BoadTest {

	private Board board;

	@Before
	public void setUp() {
		board = new Board();
	}

	@Test
	public void shouldDetermineIfHasAwinner() {
		// given
		Side sideWithOutMoves = board.getSide(0);
		for (int i = 0; i <= Board.BOARD_SIZE; i++) {
			sideWithOutMoves.set(i, 0);
		}

		Assert.assertTrue(board.getHasAwinner());
	}

	@Test
	public void shouldChangeWhosTurn() {
		// given
		int beforeTurn = board.getWhosTurn();

		// when
		board.changeWhosTurn();

		// then
		int after = board.getWhosTurn();

		Assertions.assertThat(beforeTurn).isNotEqualTo(after);
	}

	@Test
	public void shouldMoveToBigPitToFinalizeGame() {
		board.finalizeGame();
		List<Integer> pits = board.getSide(0).getPits();
		List<Integer> otherSidePits = board.getSide(0).getPits();

		Assertions.assertThat(pits).containsExactly(0, 0, 0, 0, 0, 0, 36);
		Assertions.assertThat(otherSidePits).containsExactly(0, 0, 0, 0, 0, 0, 36);
	}

}
