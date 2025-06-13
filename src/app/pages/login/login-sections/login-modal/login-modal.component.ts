import { Output, Component, EventEmitter } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { CartService } from '../../../../core/services/cart/cart.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgIf } from '@angular/common';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login-modal',
  imports: [FormsModule, NgIf, ReactiveFormsModule, CommonModule],
  templateUrl: './login-modal.component.html',
  styleUrl: './login-modal.component.css'
})
export class LoginModalComponent {
  username = '';
  password = '';
  errorMessage = '';
  isLoading = false;
  rememberPassword = false;
  showPassword: boolean = false;
  isRegistering = false;

  user: any = {
    names: '',
    lastnames: '',
    dni: '',
    socialReason: '',
    telephone: '',
    email: '',
    username: '',
    password: '',
    birthDate: ''
  };

  idRole: number = 3;

  @Output() closeModal = new EventEmitter<void>();

  constructor(
    private authService: AuthService,
    private cartService: CartService,
    private router: Router,
    private snackBar: MatSnackBar,
    private fb: FormBuilder
  ) {}

  private showSnackBar(message: string, type: string = 'snackbar-success') {
    this.snackBar.open(message, 'Cerrar', {
      duration: 3000,
      panelClass: [type]
    });
  }

  ngOnInit() {
    this.loadRememberedCredentials();
  }

  onSubmit(): void {
    this.isLoading = true;

    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        if (this.rememberPassword) {
          this.saveRememberMe();
        }

        this.showSnackBar('Sesión iniciada con éxito');
        this.close();

        this.cartService.migrateLocalCartToBackend().subscribe({
          next: () => {
            console.log('Carrito migrado exitosamente');
            window.location.reload();
          },
          error: (err) => {
            console.error('Error al migrar carrito:', err);
            window.location.reload();
          }
        });
      },
      error: (err) => {
        this.errorMessage = err.error?.message || 'Ocurrió un error en el inicio de sesión.';
        this.showSnackBar(this.errorMessage, 'error-snackbar');
        console.log('Login error', err);
      }
    }).add(() => this.isLoading = false);
  }

  register(): void {
    if (
      !this.user.username ||
      !this.user.password ||
      !this.user.email ||
      !this.user.names ||
      !this.user.lastnames ||
      !this.user.dni ||
      !this.user.socialReason ||
      !this.user.telephone ||
      !this.user.birthDate
    ) {
      console.warn('Faltan datos para registrar usuario.');
      this.showSnackBar('Completa todos los campos.', 'error-snackbar');
      return;
    }

    this.authService.registerUser(this.user, this.idRole).subscribe({
      next: (response) => {
        this.authService.saveToken(response.token, response.idUser);

        this.cartService.migrateLocalCartToBackend().subscribe({
          next: () => {
            this.showSnackBar('Cuenta creada exitosamente. Iniciando sesión...');
            setTimeout(() => {
              window.location.reload();
            }, 1500);
          },
          error: (err) => {
            console.error('Error al migrar carrito tras registro:', err);
            window.location.reload();
          }
        });
      },
      error: (error) => {
        console.error("Error al registrar usuario: ", error);
        this.showSnackBar('Error al registrar usuario.', 'error-snackbar');
      }
    });
  }


  loadRememberedCredentials(): void {
    const savedRemember = localStorage.getItem('rememberPassword') === 'true';
    this.rememberPassword = savedRemember;

    if (savedRemember) {
      this.username = localStorage.getItem('savedUsername') || '';
      this.password = localStorage.getItem('savedPassword') || '';
    }
  }

  saveRememberMe(): void {
    if (this.rememberPassword) {
      localStorage.setItem('rememberPassword', 'true');
      localStorage.setItem('savedUsername', this.username);
      localStorage.setItem('savedPassword', this.password);
    } else {
      localStorage.removeItem('rememberPassword');
      localStorage.removeItem('savedUsername');
      localStorage.removeItem('savedPassword');
    }
  }

  close(): void {
    this.closeModal.emit();
  }

  toggleForm(): void {
    this.isRegistering = !this.isRegistering;
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
}
