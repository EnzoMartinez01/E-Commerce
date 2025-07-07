import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private baseUrl = 'http://localhost:8080/api/v1/payments';

  constructor(private http: HttpClient) {}

  registerPayment(cartId: number, reference: string, file: File): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      throw new Error('Token no encontrado');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    const formData = new FormData();
    formData.append('cartId', cartId.toString());
    formData.append('reference', reference);
    formData.append('file', file);

    return this.http.post<any>(`${this.baseUrl}/registerPayment`, formData, {
      headers,
      observe: 'response'
    });
  }

  validatePayment(paymentId: number, isValid: boolean, username: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/validatePayment`, {
      paymentId,
      isValid,
      username
    });
  }

  getAllPayments(page: number = 0, size: number = 10): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/getAllPayments?page=${page}&size=${size}`);
  }
}
