package com.game.mancala.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.mancala.model.Board;
import com.game.mancala.service.MancalaService;

@RestController
@RequestMapping(value = "/game/board", produces = { "application/json" })
@CrossOrigin(origins = "http://localhost:4200")
public class GameApi {

	@Autowired
	private MancalaService mancalaService;

	@RequestMapping(method = RequestMethod.POST)
	public Board move(@RequestParam(name = "player") Integer player, @RequestParam(name = "pit") Integer pit) {
		Board moved = mancalaService.move(player, pit);
		if(moved.getHasAwinner()) {
			moved.finalizeGame();
		}
		return moved;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Board getBoard() {
		return mancalaService.getBoard();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public Board reset() {
		return mancalaService.reset();
	}
}
