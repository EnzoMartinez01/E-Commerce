import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddressesService } from '../../core/services/users/addresses.service';
import { AuthService } from '../../core/services/auth/auth.service';
import { CartService } from '../../core/services/cart/cart.service';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { Card } from 'primeng/card';
import { Panel } from 'primeng/panel';
import { RadioButtonModule } from 'primeng/radiobutton';
import { Button } from 'primeng/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-purchase-summary',
  standalone: true,
  imports: [CommonModule, FormsModule, DropdownModule, Card, Panel, RadioButtonModule, Button],
  templateUrl: './purchase-summary.component.html',
  styleUrl: './purchase-summary.component.css'
})
export class PurchaseSummaryComponent implements OnInit {
  tipoEntrega: string = 'domicilio';
  selectedAddress: any = null;
  cartItems: any[] = [];
  subtotal: number = 0;
  idCart: number = 0;
  addresses: any[] = [];
  shippingCost = 20;

  constructor(
    private router: Router,
    private authService: AuthService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    const backup = JSON.parse(localStorage.getItem('purchaseSummary') || '{}');

    this.authService.getUserInfoFromToken().subscribe({
      next: (user) => {
        this.cartService.getCartByUser(user.idUser).subscribe({
          next: (res) => {
            if (res && res.content && res.content.length > 0) {
              const cart = res.content[0];
              this.cartItems = cart.cartItems;
              this.idCart = cart.id;

              // CALCULAR EL SUBTOTAL DESDE EL FRONTEND (NO usar cart.total directamente)
              this.subtotal = this.cartItems.reduce((acc, item) => {
                const price = item.product?.priceOffer ?? item.product?.productPrice ?? 0;
                const quantity = item.quantity ?? 1;
                return acc + (price * quantity);
              }, 0);
            } else {
              this.cartItems = backup.cartItems || [];
              this.subtotal = backup.subtotal || 0;
              this.idCart = backup.id || null;
            }
          },
          error: () => {
            this.cartItems = backup.cartItems || [];
            this.subtotal = backup.subtotal || 0;
            this.idCart = backup.id || null;
          }
        });
      },
      error: () => {
        this.cartItems = backup.cartItems || [];
        this.subtotal = backup.subtotal || 0;
        this.idCart = backup.id || null;
      }
    });

    this.loadUserAddresses();
  }

  loadUserAddresses(): void {
    this.authService.getUserInfoFromToken().subscribe({
      next: (user) => {
        this.addresses = user.addresses || [];
      },
      error: (err) => console.error('Error al obtener direcciones', err)
    });
  }

  confirmarCompra() {
    if (this.tipoEntrega.toLowerCase() === 'domicilio' && (!this.selectedAddress || !this.selectedAddress.idAdress)) {
      alert('Por favor selecciona una dirección válida para el envío.');
      return;
    }

    const order = {
      id: this.idCart,
      items: this.cartItems,
      typeShipment: this.tipoEntrega.toUpperCase(),
      address: this.tipoEntrega === 'domicilio' ? this.selectedAddress : null,
      subtotal: this.subtotal,
      shippingCost: this.tipoEntrega === 'domicilio' ? this.shippingCost : 0,
      total: this.tipoEntrega === 'domicilio' ? this.subtotal + this.shippingCost : this.subtotal
    };

    localStorage.setItem('cart', JSON.stringify(order));
    localStorage.setItem('purchaseSummary', JSON.stringify(order));
    this.router.navigate(['/checkout/payment']);
  }
}
