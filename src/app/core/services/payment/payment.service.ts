import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private baseUrl = 'http://localhost:8080/api/v1/payments';

  constructor(private http: HttpClient) {}

  registerPayment(data: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}/registerPayment`, data);
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
