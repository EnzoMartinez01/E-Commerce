import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class CharacteristicsService {

  private baseUrl = 'http://localhost:8080/api/v1/characteristics';

  constructor(private http: HttpClient) {}

  getCharacteristics(
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

    return this.http.get<any>(`${this.baseUrl}/getCharacteristicsByProducts/${idProduct}`, { headers, params });
  }
}
