import {Categories} from '../../../Models/categories.model';
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface CategoryResponse{
  content: Categories[];
  totalElements: number;
}

@Injectable({
  providedIn: 'root'
})

export class CategoriesService {
  private baseUrl = '';

  constructor(private http: HttpClient) {}

  getCategories(
    page: number,
    size: number
  ): Observable<CategoryResponse> {
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

    return this.http.get<any>(`${this.baseUrl}/categories/getAll`, {headers, params});
  }
}
