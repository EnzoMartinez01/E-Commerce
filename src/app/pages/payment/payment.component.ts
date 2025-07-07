import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PaymentService } from '../../core/services/payment/payment.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from '../../core/services/auth/auth.service';
import { CartService } from '../../core/services/cart/cart.service';


@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent implements OnInit {
  paymentForm!: FormGroup;
  selectedFile: File | null = null;
  total: number = 0;
  delivery: number = 0;
  cartId: number = 0;

  constructor(
    private fb: FormBuilder,
    private paymentService: PaymentService,
    private cartService: CartService,
    private authService: AuthService,
    private router: Router
  ) {
  }


  ngOnInit(): void {
    this.initForm();
    this.authService.getUserInfoFromToken().subscribe({
      next: (user) => {
        this.cartService.getCartSummaryByUser(user.idUser).subscribe({
          next: (summary) => {
            if (summary) {
              this.cartId = summary.idCart;
              this.delivery = summary.delivery;
              this.total = summary.total;
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

  fileTouched: boolean = false;

  onFileChange(event: any) {
    this.fileTouched = true;
    const file = event.target.files?.[0];
    if (file) {
      this.selectedFile = file;
    } else {
      this.selectedFile = null;
    }
  }


  submit() {
    console.log('Form válido:', this.paymentForm.valid);
    console.log('Archivo seleccionado:', this.selectedFile);
    console.log('ID del carrito:', this.cartId);

    if (this.paymentForm.valid && this.selectedFile && this.cartId) {
      const reference = this.paymentForm.value.reference;

      this.paymentService.registerPayment(this.cartId, reference, this.selectedFile).subscribe({
        next: (response) => {
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
        }
      });
    } else {
      alert('Completa todos los campos y sube el comprobante.');
    }
  }
}
