import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
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

  validatePayment(paymentId: number, isValid: boolean | null): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      throw new Error('Token no encontrado');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    let params = new HttpParams()
      .set('paymentId', paymentId.toString());

    if (isValid !== null) {
      params = params.set('isValid', isValid.toString());
    }

    return this.http.post(`${this.baseUrl}/validatePayment`, null, {
      headers,
      params
    });
  }

  getAllPayments(
    page: number,
    size: number,
    search: string | null = null,
    status: string | null = null
  ): Observable<any> {
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

    if (search) {
      params = params.set('search', search);
    }

    if (status) {
      params = params.set('status', status);
    }

    return this.http.get<any>(`${this.baseUrl}/getAllPayments`, { params, headers });
  }


  getPaymentId(paymentId: number): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      throw new Error('Token no encontrado');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>(`${this.baseUrl}/getPayment/${paymentId}`, { headers });
  }

  downloadVoucher(paymentId: number): Observable<Blob> {
    const token = sessionStorage.getItem('authToken');
    if (!token) {
      throw new Error('Token no encontrado');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get(`${this.baseUrl}/downloadVoucher/${paymentId}`, {
      headers,
      responseType: 'blob'
    });
  }

}
