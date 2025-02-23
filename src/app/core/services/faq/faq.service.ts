import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Faq } from '../../../Models/faq.model';

export interface FaqResponse {
    content: Faq[];
    totalElements: number;
}

@Injectable({
    providedIn: 'root'
})

export class FaqService {
    private apiBaseUrl = 'http://localhost:8080/api/v1/faq';

    constructor(private http: HttpClient) { }

    // Faq
    getAllFaq(
        page: number,
        size: number
    ): Observable<FaqResponse> {
        const token = sessionStorage.getItem('authToken');
        if (!token) {
            throw new Error('Token not found.');
        }
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        let params = new HttpParams()
            .set('page', page.toString())
            .set('size', size.toString());

        return this.http.get<FaqResponse>(`${this.apiBaseUrl}/getAllFaq`, { headers, params });
    }
}
