import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ContactService {

  private baseUrl = 'http://localhost:8080/api/v1/contacs';

  constructor(private http: HttpClient) { }

  getContacts(page: number, size: number): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      throw new Error('Token no encontrado');
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<any>(`${this.baseUrl}/getAllContacts`, { headers, params });
  }

  getContactById(idContact: number): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      throw new Error('Token no encontrado');
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(`${this.baseUrl}/getContactById/${idContact}`, { headers });
  }

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

  updateContact(idContact: number, idStatus: number, answer: string): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      throw new Error('Token no encontrado');
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    let params = new HttpParams()
      .set('answer', answer.toString());

    return this.http.put<any>(`${this.baseUrl}/updatedContactStatus/${idContact}/${idStatus}`, null, { params, headers });
  }
}
