import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PaymentService } from '../../core/services/payment/payment.service';
import { Router } from '@angular/router';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from '../../core/services/auth/auth.service';
import { CartService } from '../../core/services/cart/cart.service';

import { Card } from 'primeng/card';
import { Divider } from 'primeng/divider';
import { Button } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { FileUploadModule } from 'primeng/fileupload';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    Divider,
    Button,
    InputTextModule,
    FileUploadModule,
    NgOptimizedImage
  ],
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  paymentForm!: FormGroup;
  selectedFile: File | null = null;
  total: number = 0;
  delivery: number = 0;
  cartId: number = 0;
  fileTouched: boolean = false;
  loading: boolean = false;
  qrImage = 'assets/media/qr.png';

  constructor(
    private fb: FormBuilder,
    private paymentService: PaymentService,
    private cartService: CartService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initForm();

    const resumen = JSON.parse(localStorage.getItem('purchaseSummary') || '{}');
    this.delivery = resumen.shippingCost ?? 0;

    this.authService.getUserInfoFromToken().subscribe({
      next: (user) => {
        this.cartService.getCartSummaryByUser(user.idUser).subscribe({
          next: (summary) => {
            if (summary) {
              this.cartId = summary.idCart;
              const baseTotal = summary.total || 0;
              this.total = resumen.total ?? (baseTotal + this.delivery);
            } else {
              alert('No se encontró el carrito. Redirigiendo...');
              this.router.navigate(['/home']);
            }
          },
          error: (err) => {
            console.error('Error al obtener el carrito:', err);
            this.router.navigate(['/home']);
          }
        });
      },
      error: () => {
        alert('Usuario no autenticado. Redirigiendo...');
        this.router.navigate(['/home']);
      }
    });
  }

  initForm(): void {
    this.paymentForm = this.fb.group({
      reference: ['', Validators.required]
    });
  }

  onFileChange(event: any) {
    this.fileTouched = true;
    const file = event.target.files?.[0];

    const allowedTypes = ['image/jpeg', 'image/png', 'application/pdf'];
    const maxSize = 5 * 1024 * 1024; // 5MB

    if (!file || !allowedTypes.includes(file.type)) {
      alert('Archivo no válido. Solo se permiten JPG, PNG o PDF.');
      this.selectedFile = null;
      return;
    }

    if (file.size > maxSize) {
      alert('Archivo demasiado grande. Máximo 5MB.');
      this.selectedFile = null;
      return;
    }

    this.selectedFile = file;
  }

  submit() {
    if (this.paymentForm.valid && this.selectedFile && this.cartId) {
      this.loading = true;
      const reference = this.paymentForm.value.reference;

      this.paymentService.registerPayment(this.cartId, reference, this.selectedFile).subscribe({
        next: (response) => {
          this.loading = false;
          if (response.status === 201) {
            alert('Pago enviado correctamente. Recibirás confirmación por correo.');
            this.router.navigate(['/home']);
          } else {
            alert('Respuesta inesperada del servidor.');
          }
        },
        error: (err) => {
          console.error('Error en la respuesta del servidor:', err);
          alert('Error al enviar el pago. Intenta nuevamente.');
          this.loading = false;
        }
      });
    } else {
      alert('Completa todos los campos y sube el comprobante.');
    }
  }
}
