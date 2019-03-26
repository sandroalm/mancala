package com.game.mancala.model;

public class Board {

	public static final int BOARD_SIZE = 5;
	public static final int BIG_PILE_INDEX = BOARD_SIZE + 1;
	private Side[] sides = { new Side(0), new Side(1) };
	private Integer currentPlayer = 0;

	public void changeWhosTurn() {
		currentPlayer = 1 - currentPlayer;
	}

	public Integer getWhosTurn() {
		return currentPlayer;
	}

	public Side getSide(int i) {
		return sides[i];
	}

	public Side[] getSides() {
		return sides;
	}

	public boolean getHasAwinner() {
		return !sides[0].hasMoviments() || !sides[1].hasMoviments();
	}

	public void finalizeGame() {
		Integer valuePLayer1 = 0;
		Integer valuePLayer2 = 0;
		for (int i = 0; i <= BOARD_SIZE; i++) {
			valuePLayer1 += sides[0].getValue(i);
			valuePLayer2 += sides[1].getValue(i);

			sides[0].clear(i);
			sides[1].clear(i);
		}

		sides[0].add(BOARD_SIZE + 1, valuePLayer1);
		sides[1].add(BOARD_SIZE + 1, valuePLayer2);
	}

}
