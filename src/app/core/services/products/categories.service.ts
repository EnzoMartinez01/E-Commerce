import {Categories} from '../../../Models/categories.model';
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';

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
    size: number,
  ): Observable<CategoryResponse> {

    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<any>(`${this.baseUrl}/getAllCategories`, {params});
  }

  getCategoryByName(name: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getCategoryByName/${name}`);
  }

  getCategoryById(id:number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getCategory/${id}`);
  }

  // Updated Product
  updateCategory(idCategory: number, updatedCategory: any): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      console.error('Token no encontrado');
      return throwError(() => new Error('Token no encontrado'));
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.put<any>(`${this.baseUrl}/updateCategory/${idCategory}`, updatedCategory, { headers });
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deleteCategory/${id}`);
  }
}
