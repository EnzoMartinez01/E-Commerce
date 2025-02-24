import { Output } from '@angular/core';
import { Component, EventEmitter } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login-modal',
  imports: [FormsModule, NgIf, ReactiveFormsModule],
  templateUrl: './login-modal.component.html',
  styleUrl: './login-modal.component.css'
})

export class LoginModalComponent {
  username = '';
  password = '';
  errorMessage = '';
  isLoading = false;

  isRegistering = false;
  registerForm: FormGroup;

  @Output() closeModal = new EventEmitter<void>();

  constructor(private authService: AuthService,
              private router: Router,
              private snackBar: MatSnackBar,
              private fb: FormBuilder) {
    this.registerForm = this.fb.group({
      names: ['', Validators.required],
      lastName: ['', Validators.required],
      dni: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      socialReason: [''],
      telephone: ['', [Validators.required, Validators.pattern(/^\d{9}$/)]],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      birthDate: ['', Validators.required],
    });
  }

  private showSnackBar(message: string, type: string = 'snackbar-success') {
    this.snackBar.open(message, 'Cerrar', {
      duration: 3000,
      panelClass: [type]
    });
  }

  onSubmit(): void {
    this.isLoading = true;
    this.authService.login(this.username, this.password).subscribe(
      (response) => {
        this.showSnackBar('Sesión iniciada con éxito');
        this.authService.saveToken(response.token);
        this.router.navigate(['/home']);
      },
      (err) => {
        this.errorMessage = err.error.message || 'Ocurrió un error en el inicio de sesión.';
        this.showSnackBar(this.errorMessage, 'error-snackbar');
        console.log('Login error', err);
      }
    ).add(() => this.isLoading = false);
  }

  close() {
    console.log("Cerrando modal...");
    this.closeModal.emit();
  }

  toggleForm(): void {
    this.isRegistering = !this.isRegistering;
  }
}
