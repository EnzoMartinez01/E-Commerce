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
  private baseUrl = 'http://localhost:8080/api/v1/categories';

  constructor(private http: HttpClient) {}

  getCategories(
    page: number,
    size: number
  ): Observable<CategoryResponse> {

    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<any>(`${this.baseUrl}/getAllCategories`, {params});
  }

  getCategoryByName(name: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getCategoryByName/${name}`);
  }
}
