package com.game.mancala.model;

public class Board {

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

}
