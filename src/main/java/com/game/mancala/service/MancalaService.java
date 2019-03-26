package com.game.mancala.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.game.mancala.model.Board;
import com.game.mancala.model.Side;

@Service
public class MancalaService {

	private Board board = new Board();  

	public Board move(int originalPlayer, int pit) {
		Side side = board.getSide(originalPlayer);
		List<Integer> pits = side.getPits();
		Integer toMove = pits.get(pit);
		side.clear(pit);

		for (int i = 0; i < toMove; i++) {
			pit++;
			boolean isOriginalPlayerSide = originalPlayer == side.getPlayer();
			boolean oponentsBigPit = pit == pits.size() - 1 && !isOriginalPlayerSide;
			if (pit == pits.size() || oponentsBigPit) {
				side = anotherSide(side);
				pit = 0;
			}

			side.add(pit, 1);
			pits = side.getPits();
		}

		Integer lastValue = side.getValue(pit);
		boolean isBigPile = pit >= 6;
		boolean needsToGetTheOposite = !isBigPile && lastValue == 1 && (originalPlayer == side.getPlayer());
		if (needsToGetTheOposite) {
			movePitAndOpositeTotheBigPit(pit, side);
		}

		if (!isBigPile) {
			board.changeWhosTurn();
		}

		return board;
	}

	private List<Integer> movePitAndOpositeTotheBigPit(int pit, Side side) {
		Integer value = side.getValue(pit);
		Side otherSide = anotherSide(side);
		int opositeIndex = 5 - pit;
		value = otherSide.getValue(opositeIndex);
		otherSide.clear(opositeIndex);
		side.add(6, value + 1);
		side.clear(pit);
		return side.getPits();
	}

	

	private Side anotherSide(Side side) {
		return board.getSide(1 - side.getPlayer());
	}

	public Board getBoard() {
		return board;
	}	

}
