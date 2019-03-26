import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class GameService {

    private BASE_URL = '//localhost:8080/game/board';

    constructor(private http: HttpClient) { }

    move(side: number, pit: number): Observable<any> {
        return this.http.post(this.BASE_URL + '?player=' + side + '&pit=' + pit, null);
    }

    getSides(): Observable<any> {
        return this.http.get(this.BASE_URL);
    }

    reset(): any {
        return this.http.delete(this.BASE_URL);
    }
}
