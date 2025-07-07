import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { HttpClient } from '@angular/common/http';
import { PaymentService } from '../../core/services/payment/payment.service'; // ajusta la ruta si es necesario

@Component({
  selector: 'app-payment-admin',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule],
  templateUrl: './payment-admin.component.html',
  styleUrls: ['./payment-admin.component.css']
})
export class PaymentAdminComponent implements OnInit {
  payments: any[] = [];
  loading: boolean = true;

  constructor(private paymentService: PaymentService, private http: HttpClient) {}

  ngOnInit(): void {
    this.loadPendingPayments();
  }

  loadPendingPayments() {
    this.paymentService.getPendingPayments().subscribe({
      next: (res) => {
        this.payments = res;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error cargando pagos pendientes', err);
        this.loading = false;
      }
    });
  }

  validate(paymentId: number, isValid: boolean) {
    this.paymentService.validatePayment(paymentId, isValid).subscribe({
      next: () => {
        this.payments = this.payments.filter(p => p.id !== paymentId);
        alert(isValid ? 'Pago validado' : 'Pago rechazado');
      },
      error: () => alert('Error al procesar la validación.')
    });
  }
}
