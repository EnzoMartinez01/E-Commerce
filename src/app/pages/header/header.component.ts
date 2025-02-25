import { Component, OnInit, EventEmitter } from '@angular/core';
import { AuthService } from '../../core/services/auth/auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatTableDataSource} from "@angular/material/table";
import { MatInputModule } from '@angular/material/input';
import { ProductsService } from '../../core/services/products/products.service';
import { Products } from '../../Models/products.model';
import { NgIf, NgFor, CommonModule } from '@angular/common';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { RouterModule } from '@angular/router';
import { Output } from '@angular/core';
import { LoginModalComponent } from "../login/login-sections/login-modal/login-modal.component";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    NgIf,
    NgFor,
    CommonModule,
    IconFieldModule,
    InputIconModule,
    RouterModule,
]

})
export class HeaderComponent implements OnInit{

  @Output() openLogin = new EventEmitter<void>();

  openLoginModal() {
    console.log("Abiendo Modal...");
    this.openLogin.emit();
  }

  menuItems = [
    { label: 'Inicio', link: '/home' },
    { label: 'Nosotros', link: '/about' },
    { label: 'Categorias', link: '/categories' },
    { label: 'FAQ', link: '/FAQ' },
    { label: 'Contacto', link: '/contact' }
  ];

  filters = {
    searchTerms: ''
  };

  allProducts: string[] = [];
  filteredProducts: string[] = [];

  dataSource = new MatTableDataSource<Products>();

  menuOpen = false;

  constructor(private authService: AuthService,
              private router: Router,
              private snackBar: MatSnackBar,
              private productsService: ProductsService) { }

  ngOnInit(): void {
    this.loadProducts();
  }



  // Navigation - Hamburguer
  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }


  closeMenu(): void {
    this.menuOpen = false;
  }

  // Filter Products for Search Input
  loadProducts(): void {
    const { searchTerms } = this.filters;

    this.productsService.getProductsFilter(null, null, null, null, null, null, 1, 10, searchTerms).subscribe(
      (data) => {
        console.log('Productos recibidos:', data);
        this.allProducts = data.content.map(producto => producto.productName);
        this.filterProducts();
      },
      (error) => {
        console.error('Error al cargar productos:', error);
      }
    );
  }

  // Método para filtrar productos dinámicamente
  filterProducts(): void {
    const searchTerm = this.filters.searchTerms.toLowerCase();
    this.filteredProducts = this.allProducts.filter(producto =>
      producto.toLowerCase().includes(searchTerm)
    );
  }

  // Logout
  logout(): void {
    this.authService.logout().subscribe(
      (response) => {
        this.snackBar.open('Closing session', 'Close', {
          duration: 3000,
        panelClass: ['snackbar-success']
        });
        console.log('Logout successfull', response);
        sessionStorage.removeItem('authToken');
        window.location.reload();
      },
      (error) => {
        this.snackBar.open('Error logging out', 'Close', {
          duration: 3000,
          panelClass: ['snackbar-error']
        });
        console.error('Logout error', error);
      }
    );
  }
  //Abrir Modal Login
}
