import {Brands} from '../../../Models/brands.model';
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';

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
    getBrands(
        page: number,
        size: number,
      ): Observable<BrandsResponse> {
    
        let params = new HttpParams()
          .set('page', page.toString())
          .set('size', size.toString());
    
        return this.http.get<any>(`${this.baseUrl}/getAllBrands`, {params});
      }

  getAllBrandsImages(): Observable<string[]> {

    return this.http.get<string[]>(`${this.baseUrl}/getAllBrandsImages`);
  }

  getBrandsById(idBrand:number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getBrands/${idBrand}`);
  }

  updateBrand(idBrand: number, updatedBrand: any): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      console.error('Token no encontrado');
      return throwError(() => new Error('Token no encontrado'));
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.put<any>(`${this.baseUrl}/updateBrand/${idBrand}`, updatedBrand, { headers });
  }

  deleteBrands(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deleteBrands/${id}`);
  }

}
