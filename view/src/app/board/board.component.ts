import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';

@Component({
    selector: 'app-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

    board: any;
    firstSide: Side;
    secondeSide: Side;
    currentPlayer: number;
    constructor(private _gameService: GameService) {
    }

    ngOnInit() {
        this._gameService.getSides().subscribe(result => {
            this.updateBoardValues(result);
        });
    }

    move(side: number, pit: number) {
        this._gameService.move(side, pit).subscribe(result => {
            this.updateBoardValues(result);
        });
    }

    reset() {
        this._gameService.reset().subscribe(result => {
            this.updateBoardValues(result);
        });
    }

    private updateBoardValues(result) {
        if (result) {
            this.board = result;
            this.firstSide = result.sides[0];
            this.secondeSide = result.sides[1];
            this.currentPlayer = result.whosTurn;
        }
    }
}
