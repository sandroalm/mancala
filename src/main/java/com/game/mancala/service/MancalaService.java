package com.game.mancala.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.game.mancala.model.Board;
import com.game.mancala.model.Side;

import static com.game.mancala.model.Board.BIG_PILE_INDEX;
import static com.game.mancala.model.Board.BOARD_SIZE;

@Service
public class MancalaService {

	private Board board = new Board();

	public Board move(int originalPlayer, int pitIndex) {
		Side currentSide = board.getSide(originalPlayer);
		List<Integer> pits = currentSide.getPits();

		Integer numberOfMoves = pits.get(pitIndex);

		if (originalPlayer == board.getWhosTurn() && numberOfMoves != 0) {
			currentSide.clear(pitIndex);

			for (int i = 0; i < numberOfMoves; i++) {
				// move one by one
				pitIndex++;

				boolean isBigPit = pitIndex == BIG_PILE_INDEX;
				boolean isOponentsBigPit = isBigPit && originalPlayer != currentSide.getPlayer();
				boolean isOneSideDone = pitIndex == pits.size();
				if (isOneSideDone || isOponentsBigPit) {
					currentSide = anotherSide(currentSide);
					pits = currentSide.getPits();
					pitIndex = 0;
				}
				

				currentSide.add(pitIndex, 1);
			}

			// last Value
			boolean isBigPile = pitIndex == BIG_PILE_INDEX;

			if (!isBigPile) {
				boolean needsToGetTheOposite = currentSide.getValue(pitIndex) == 1
						&& (originalPlayer == currentSide.getPlayer());
				if (needsToGetTheOposite) {
					captureStones(pitIndex, currentSide);
				}

				board.changeWhosTurn();
			}
		}
		return board;
	}

	private List<Integer> captureStones(int pit, Side side) {
		Side otherSide = anotherSide(side);
		int opositeIndex = BOARD_SIZE - pit;
		Integer value = otherSide.getValue(opositeIndex);
		otherSide.clear(opositeIndex);
		side.add(BIG_PILE_INDEX, value + 1);
		side.clear(pit);
		return side.getPits();
	}

	public Board getBoard() {
		return board;
	}

	private Side anotherSide(Side side) {
		return board.getSide(1 - side.getPlayer());
	}

	public Board reset() {
		board = new Board();
		return board;
	}

}
