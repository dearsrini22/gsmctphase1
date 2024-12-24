import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";
import {AuthService} from "./auth.service";

@Injectable({
    providedIn: 'root'
})
export class WebApiService {
    constructor(private httpClient: HttpClient, private authService: AuthService) {
    }

    get(url: string): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Cache-Control': 'no-cache',
                'Pragma': 'no-cache',
                'Authorization': 'Bearer ' + this.authService.getToken()
            }),
            observe: "response" as 'body'
        };
        return this.httpClient.get(
            url,
            httpOptions
        ).pipe(map((response: any) => this.ReturnResponseData(response)), catchError(this.handleError));
    }

    delete(url: string): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Cache-Control': 'no-cache',
                'Pragma': 'no-cache',
                'Authorization': 'Bearer ' + this.authService.getToken()
            }),
            observe: "response" as 'body'
        };
        return this.httpClient.delete(
            url,
            httpOptions
        ).pipe(map((response: any) => this.ReturnResponseData(response)), catchError(this.handleError));
    }

    post(url: string, model: any): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.authService.getToken()
            }),
            observe: "response" as 'body'
        };
        return this.httpClient.post(url, model, httpOptions)
            .pipe(map((response: any) => this.ReturnResponseData(response)), catchError(this.handleError));
    }

    private ReturnResponseData(response: any) {
        return response;
    }

    private handleError(error: any) {
        return throwError(error);
    }
}
