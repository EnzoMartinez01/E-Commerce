import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../../core/services/payment/payment.service';
import { PaymentDto } from '../../Models/payment.model';
import {FormsModule} from '@angular/forms';
import {TableModule} from 'primeng/table';
import {DatePipe, DecimalPipe, NgIf} from '@angular/common';
import {Button} from 'primeng/button';
import {Ripple} from 'primeng/ripple';
import {Dialog} from 'primeng/dialog';

@Component({
  selector: 'app-payment-admin',
  templateUrl: './payment-admin.component.html',
  styleUrls: ['./payment-admin.component.css'],
  standalone: true,
  imports: [
    FormsModule,
    TableModule,
    DecimalPipe,
    Button,
    Ripple,
    Dialog,
    DatePipe,
    NgIf
  ]
})
export class PaymentAdminComponent implements OnInit {
  payments: PaymentDto[] = [];
  filteredPayments: PaymentDto[] = [];
  filterReference: string = '';
  selectedStatus: string | null = null;
  loading = true;
  selectedPayment: PaymentDto = {} as PaymentDto;

  viewDialog: boolean = false;

  constructor(private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.loadAllPayments();
  }

  loadAllPayments(): void {
    this.paymentService.getAllPayments(0, 100, this.filterReference, this.selectedStatus).subscribe({
      next: (res) => {
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
    this.loadAllPayments();
  }

  validate(id: number, valid: boolean): void {
    this.paymentService.validatePayment(id, valid).subscribe({
      next: () => {
        console.log(`Pago ${id} ${valid ? 'verificado' : 'rechazado'} correctamente`);
        this.loadAllPayments();
      },
      error: (err) => {
        console.error('Error al validar pago:', err);
      }
    });
  }

  viewFile(payment: PaymentDto): void {
    this.paymentService.downloadVoucher(payment.idPayment).subscribe({
      next: (response: Blob) => {
        const file = new Blob([response], { type: response.type });

        const fileURL = window.URL.createObjectURL(file);
        window.open(fileURL, '_blank');
      },
      error: (err) => {
        console.error('Error al visualizar el comprobante:', err);
      }
    });
  }



  // View Payment
  viewPayment(payment: PaymentDto) {
    this.selectedPayment = { ...payment };
    this.viewDialog = true;

    this.paymentService.getPaymentId(this.selectedPayment.idPayment).subscribe(
      (data) => {
        console.log('Pago obtenido:', data);
      },
      (error) => {
        console.log('Error al obtener el pago:', error);
      }
    );
  }
}
