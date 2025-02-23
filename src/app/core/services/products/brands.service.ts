import {Brands} from '../../../Models/brands.model';
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface BrandsResponse {
    content: Brands[];
    totalElements: number;
}

@Injectable({
    providedIn: 'root'
})

export class BrandsService {
    private baseUrl = 'http://localhost:8080/api/v1/brands';

    constructor(private http: HttpClient) {}

  getAllBrandsImages(): Observable<string[]> {
    const token = sessionStorage.getItem('authToken');

    if (!token) {
      throw new Error('Token not found.');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<string[]>(`${this.baseUrl}/getAllBrandsImages`, { headers });
  }

}
