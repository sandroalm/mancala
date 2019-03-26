import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';

@Component({
    selector: 'app-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

    sides: any;
    firstSide: Side;
    secondeSide: Side;
    currentPlayer: number;
    constructor(private _gameService: GameService) {
    }

    ngOnInit() {
        this._gameService.getSides().subscribe(result => {
            this.sides = result;
            if (result) {
                this.firstSide = result.sides[0];
                this.secondeSide = result.sides[1];
                this.currentPlayer = result.whosTurn;
            }
        });
    }

    move(side: number, pit: number) {
        this._gameService.move(side, pit).subscribe(result => {
            this.sides = result;
            if (result) {
                this.firstSide = result.sides[0];
                this.secondeSide = result.sides[1];
                this.currentPlayer = result.whosTurn;
            }
        });
    }

}
