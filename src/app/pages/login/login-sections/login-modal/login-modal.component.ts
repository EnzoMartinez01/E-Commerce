import { Output } from '@angular/core';
import { Component, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login-modal',
  imports: [FormsModule, NgIf],
  templateUrl: './login-modal.component.html',
  styleUrl: './login-modal.component.css'
})

export class LoginModalComponent {
  username = '';
  password = '';
  errorMessage = '';
  isLoading = false;

  constructor(private authService: AuthService,
              private router: Router,
              private snackBar: MatSnackBar) {}

  onSubmit(): void {
    this.isLoading = true;
    this.authService.login(this.username, this.password).subscribe(
      (response) => {
        this.snackBar.open('Sesión iniciada con éxito', 'Cerrar', {
          duration: 3000,
          panelClass: ['snackbar-success']
        });
        this.isLoading = false;
        const token = response.token;
        this.authService.saveToken(token);
        this.router.navigate(['/home']);
      },
      (err) => {
        this.isLoading = false;
        if (err.status === 400) {
          this.snackBar.open('Datos incorrectos. Intenta nuevamente.', 'Cerrar', {
            duration: 3000,
            panelClass: ['error-snackbar'],
          });
        } else {
          this.snackBar.open('Error en el inicio de sesión. Intenta nuevamente.', 'Cerrar', {
            duration: 3000,
            panelClass: ['error-snackbar'],
          });
        }
        this.errorMessage = err.error.message || 'Ocurrió un error en el inicio de sesión.';
        console.log('Login error', err);
      }
    );
  }
  
  @Output() closeModal = new EventEmitter<void>();

  close() {
    console.log("Cerrando modal...");
    this.closeModal.emit();
  }
}
