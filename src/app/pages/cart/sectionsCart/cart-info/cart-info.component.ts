import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from '../../../../core/services/cart/cart.service';
import { ButtonDirective } from 'primeng/button';
import { AuthService } from '../../../../core/services/auth/auth.service';
import { RouterLink, Router } from '@angular/router';
import { ProductsService } from '../../../../core/services/products/products.service';

@Component({
  selector: 'app-cart-info',
  standalone: true,
  imports: [CommonModule, ButtonDirective, RouterLink],
  templateUrl: './cart-info.component.html',
  styleUrl: './cart-info.component.css'
})
export class CartInfoComponent implements OnInit {
  cart: any = { cartItems: [] };
  subtotal: number = 0;

  constructor(
    private cartService: CartService,
    private authService: AuthService,
    private productsService: ProductsService,
    private router: Router
  ) {}

  goToConfirmacion() {
    const payload = {
      cartItems: this.cart.cartItems,
      subtotal: this.subtotal
    };
    localStorage.setItem('purchaseSummary', JSON.stringify(payload));

    this.router.navigate(['/purchase-summary'], { state: payload });
  }

  ngOnInit(): void {
    const token = sessionStorage.getItem('authToken');

    if (!token) {
      this.loadLocalCart();
    } else {
      this.authService.getUserInfoFromToken().subscribe({
        next: (user: any) => {
          if (!user || !user.idUser) {
            console.error('Usuario inválido');
            return;
          }

          this.cartService.getCartByUser(user.idUser).subscribe(response => {
            if (response && response.content?.length > 0) {
              this.cart = response.content[0];
              this.calcularSubtotal();
            } else {
              this.cart.cartItems = [];
              this.subtotal = 0;
            }
          });
        },
        error: (error) => {
          console.error('Error obteniendo usuario:', error);
        }
      });
    }
  }

  loadLocalCart() {
    const localCart = JSON.parse(localStorage.getItem('localCart') || '[]');
    if (localCart.length === 0) {
      this.cart.cartItems = [];
      this.subtotal = 0;
      return;
    }

    this.cart.cartItems = [];

    for (const item of localCart) {
      this.productsService.getProductsById(item.idProduct).subscribe(product => {
        this.cart.cartItems.push({
          product: product,
          quantity: item.quantity,
          subTotal: (product.priceOffer ?? product.productPrice) * item.quantity
        });
        this.calcularSubtotal();
      });
    }
  }

  updateQuantity(cartItem: any, change: number) {
    const token = sessionStorage.getItem('authToken');
    const newQuantity = cartItem.quantity + change;

    if (!token) {
      const localCart = JSON.parse(localStorage.getItem('localCart') || '[]');
      const index = localCart.findIndex((item: any) => item.idProduct === cartItem.product.idProduct);

      if (index !== -1) {
        if (newQuantity < 1) {
          if (confirm("¿Deseas eliminar este producto del carrito?")) {
            localCart.splice(index, 1);
            this.cart.cartItems.splice(index, 1);
            localStorage.setItem('localCart', JSON.stringify(localCart));
            this.calcularSubtotal();
          }
        } else {
          localCart[index].quantity = newQuantity;
          cartItem.quantity = newQuantity;
          cartItem.subTotal = (cartItem.product.priceOffer ?? cartItem.product.productPrice) * newQuantity;
          localStorage.setItem('localCart', JSON.stringify(localCart));
          this.calcularSubtotal();
        }
      }
      return;
    }

    if (newQuantity < 1) {
      if (confirm("¿Deseas eliminar este producto del carrito?")) {
        this.cartService.deleteCartItem(cartItem.idCartItem).subscribe(() => {
          this.cart.cartItems = this.cart.cartItems.filter(
            (item: any) => item.idCartItem !== cartItem.idCartItem
          );
          this.calcularSubtotal();
        });
      }
      return;
    }

    this.cartService.updateCartItemQuantity(cartItem.idCartItem, newQuantity)
      .subscribe(updatedItem => {
        if (updatedItem) {
          cartItem.quantity = updatedItem.quantity;
          cartItem.subTotal = updatedItem.subTotal;
        }
        this.calcularSubtotal();
      });
  }

  calcularSubtotal() {
    if (this.cart.cartItems?.length) {
      this.subtotal = this.cart.cartItems.reduce(
        (acc: number, item: any) =>
          acc + (
            (item.product?.priceOffer ?? item.product?.productPrice ?? 0) *
            (item.quantity ?? 1)
          ), 0
      );
      this.cart.total = this.subtotal;
    } else {
      this.subtotal = 0;
      this.cart.total = 0;
    }
  }
}
