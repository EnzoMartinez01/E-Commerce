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
    private ApibaseUrl = 'http://localhost:8080/api/v1/faq';

    constructor(private http: HttpClient) { }

    // Faq
    getAllFaq(
        page: number,
        size: number
    ): Observable<FaqResponse> {

        let params = new HttpParams()
            .set('page', page.toString())
            .set('size', size.toString());

        return this.http.get<FaqResponse>(`${this.ApibaseUrl}/getAllFaq`, { params });
    }
    getFaqById(idFaq:number): Observable<any> {
      return this.http.get<any>(`${this.ApibaseUrl}/getFaq/${idFaq}`);
    }

    // Updated Faq
  updateFaqs(idFaq: number, updatedFaqs: any): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      console.error('Token no encontrado');
      return throwError(() => new Error('Token no encontrado'));
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.put<any>(`${this.ApibaseUrl}/updateFaq/${idFaq}`, updatedFaqs, { headers });
  }

  //delete
  deleteFaq(idFaq: number): Observable<void> {
    return this.http.delete<void>(`${this.ApibaseUrl}/deleteFaq/${idFaq}`);
  }
}

