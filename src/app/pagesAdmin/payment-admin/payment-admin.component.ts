import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../../core/services/payment/payment.service';
import { PaymentDto } from '../../Models/payment.model';
import {FormsModule} from '@angular/forms';
import {TableModule} from 'primeng/table';
import {DecimalPipe} from '@angular/common';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-payment-admin',
  templateUrl: './payment-admin.component.html',
  styleUrls: ['./payment-admin.component.css'],
  standalone: true,
  imports: [
    FormsModule,
    TableModule,
    DecimalPipe,
    Button
  ]
})
export class PaymentAdminComponent implements OnInit {
  payments: PaymentDto[] = [];
  filteredPayments: PaymentDto[] = [];
  filterReference: string = '';
  loading = true;

  constructor(private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.loadAllPayments();
  }

  loadAllPayments(): void {
    this.paymentService.getAllPayments(0, 100).subscribe({
      next: (res) => {
        console.log('Respuesta completa del backend:', res);
        this.payments = res.content;
        this.filteredPayments = res.content;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error cargando pagos', err);
        this.loading = false;
      }
    });
  }


  filterPayments(): void {
    const query = this.filterReference.toLowerCase().trim();
    this.filteredPayments = this.payments.filter((pago) =>
      pago.reference.toLowerCase().includes(query) ||
      pago.username.toLowerCase().includes(query)
    );
  }

  validate(id: number, valid: boolean): void {
    const username = localStorage.getItem('username');
    if (!username) {
      console.error('Usuario no autenticado');
      return;
    }

    this.paymentService.validatePayment(id, valid, username).subscribe({
      next: () => {
        console.log(`Pago ${id} ${valid ? 'verificado' : 'rechazado'} correctamente`);
        this.loadAllPayments();
      },
      error: (err) => {
        console.error('Error al validar pago:', err);
      }
    });
  }

  getVoucherUrl(pago: any): string {
    return `/uploads/payments/${pago.dni}/${pago.voucherFile}`;
  }
}
