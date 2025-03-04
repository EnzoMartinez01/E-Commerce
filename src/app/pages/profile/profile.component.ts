import { Component } from '@angular/core';
import {AuthService} from '../../core/services/auth/auth.service';
import {Card} from 'primeng/card';
import {Button} from 'primeng/button';
import {DatePipe, DecimalPipe, NgClass, NgForOf, NgIf} from '@angular/common';
import {CartService} from '../../core/services/cart/cart.service';

@Component({
  selector: 'app-profile',
  imports: [
    Card,
    Button,
    NgClass,
    DatePipe,
    NgForOf,
    NgIf
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  selectedUser: any = {};
  userInitial: string = "U";
  cartItems: any[] = [];

  constructor(private authService: AuthService,
              private cartService: CartService) {}

  ngOnInit(): void {
    this.loadUser();
    this.userInitial = this.getUserInitial();
  }

  loadUser(): void {
    const token = this.authService.getToken();
    if (token) {
      this.authService.getUserInfoFromToken().subscribe(
        (data) => {
          console.log('Información del usuario:', data);
          this.selectedUser = data;
          this.loadCartItems(data.idUser);
        },
        (error) => {
          console.error('Error al obtener el usuario:', error);
        }
      );
    }
  }

  getUserInitial(): string {
    const token = this.authService.getToken();
    if (token) {
      const userName = this.authService.getUsernameFromToken(token) || "U";
      return userName.charAt(0).toUpperCase();
    }
    return "U";
  }

  // Cargar productos en el carrito
  loadCartItems(userId: number): void {
    this.cartService.getCartByUser(userId, 0, 100).subscribe(
      (response) => {
        console.log('Respuesta completa del carrito:', response);

        if (response?.content?.length > 0) {
          this.cartItems = response.content[0].cartItems.map((item: { product: { productName: any; productImg: any; productPrice: any; productDescription: any; category: any; }; quantity: any; }) => ({
            name: item.product?.productName,
            imageUrl: item.product?.productImg,
            price: item.product?.productPrice,
            description: item.product?.productDescription || 'Sin descripción',
            quantity: item.quantity,
            category: item.product?.category || 'Sin categoría'
          }));
        } else {
          this.cartItems = [];
        }

        console.log('Productos del carrito procesados:', this.cartItems);
      },
      (error) => {
        console.error('Error al obtener los productos del carrito:', error);
      }
    );
  }

}
