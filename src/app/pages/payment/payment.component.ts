import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PaymentService } from '../../core/services/payment/payment.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent implements OnInit {
  paymentForm!: FormGroup;
  selectedFile!: File;
  total: number = 0;
  envio: number = 0;
  cartId!: number;

  constructor(
    private fb: FormBuilder,
    private paymentService: PaymentService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const cart = JSON.parse(localStorage.getItem('cart') || '{}');
    this.total = cart.total || 0;
    this.envio = cart.shippingCost || 0;
    this.cartId = cart.id;

    this.paymentForm = this.fb.group({
      reference: ['', Validators.required]
    });
  }

  onFileChange(event: any) {
    this.selectedFile = event.target.files[0];
  }

  submit() {
    if (this.paymentForm.valid && this.selectedFile) {
      const formData = new FormData();
      formData.append('cartId', this.cartId.toString());
      formData.append('reference', this.paymentForm.value.reference);
      formData.append('file', this.selectedFile);

      this.paymentService.registerPayment(formData).subscribe({
        next: () => {
          alert('Pago enviado correctamente. Recibirás confirmación por correo.');
          this.router.navigate(['/home']);
        },
        error: () => alert('Error al enviar el pago. Intenta nuevamente.')
      });
    } else {
      alert('Completa todos los campos y sube el comprobante.');
    }
  }
}
