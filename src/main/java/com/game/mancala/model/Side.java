package com.game.mancala.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Side {

	private final List<Integer> pits = new ArrayList<>(Arrays.asList(6, 6, 6, 6, 6, 6, 0));
	private int bigPit;
	private final int player;

	public Side(int player) {
		this.player = player;
	}

	public List<Integer> getPits() {
		return pits;
	}

	public int iBigPit() {
		return bigPit;
	}

	public void setBigPit(int bigPit) {
		this.bigPit = bigPit;
	}

	public int getPlayer() {
		return player;
	}

	public void set(int index, int value) {
		pits.set(index, value);
	}

	public void add(int index, int value) {
		set(index, getValue(index) + value);
	}

	public void clear(int index) {
		pits.set(index, 0);
	}

	public Integer getValue(int index) {
		return pits.get(index);
	}

}
