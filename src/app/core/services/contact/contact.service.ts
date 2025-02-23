import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ContactService {

  private baseUrl = 'http://localhost:8080/api/v1/contacs';

  constructor(private http: HttpClient) { }

  registerContactForm(contactData: any): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      throw new Error('Token no encontrado');
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<any>(`${this.baseUrl}/addContact`, contactData, { headers });
  }
}
