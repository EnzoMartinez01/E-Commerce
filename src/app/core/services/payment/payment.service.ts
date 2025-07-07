import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
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


  getPendingPayments() {
    return this.http.get<any[]>('http://localhost:8080/api/v1/payments/pending');
  }

  validatePayment(paymentId: number, isValid: boolean) {
    const params = new FormData();
    params.append('paymentId', paymentId.toString());
    params.append('isValid', isValid.toString());
    return this.http.post('http://localhost:8080/api/v1/payments/validatePayment', params);
  }

}
