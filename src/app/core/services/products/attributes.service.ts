import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AttributesService {

  private baseUrl = 'http://localhost:8080/api/v1/attributes';

  constructor(private http: HttpClient) {}

  getAttributes(
    idProduct: number | null,
    page: number,
    size: number
  ): Observable<any> {
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

    return this.http.get<any>(`${this.baseUrl}/getAttributesByProducts/${idProduct}`, { headers, params });
  }
}
