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
  total: number = 0;
  igv: number = 0;
  idCart: number = 0;
  addresses: any[] = [];
  shippingCost = 20;

  constructor(
    private router: Router,
    private authService: AuthService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {

    this.authService.getUserInfoFromToken().subscribe({
      next: (user) => {
        this.cartService.getCartByUser(user.idUser).subscribe({
          next: (res) => {
            if (res && res.content && res.content.length > 0) {
              const cart = res.content[0];
              this.cartItems = cart.cartItems;
              this.idCart = cart.id;
              this.igv = cart.igv;
              this.total = cart.total;

              console.log('Carrito recibido:', this.cartItems);

              // CALCULAR EL SUBTOTAL DESDE EL FRONTEND
              this.subtotal = this.cartItems.reduce((acc, item) => {
                const price = item.product?.priceOffer ?? item.product?.productPrice ?? 0;
                const quantity = item.quantity ?? 1;
                return acc + (price * quantity);
              }, 0);
            } else {
              alert('El carrito está vacio.')
            }
          },
          error: () => {
            console.log(this.cartService.getCartByUser(user));
          }
        });
      },
      error: () => {
        console.log('usuario no encontrado: ', this.authService.getUserInfoFromToken());
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
      igv: this.igv,
      shippingCost: this.tipoEntrega === 'domicilio' ? this.shippingCost : 0,
      total: this.tipoEntrega === 'domicilio' ? this.total + this.shippingCost : this.total
    };

    localStorage.setItem('cart', JSON.stringify(order));
    localStorage.setItem('purchaseSummary', JSON.stringify(order));
    this.router.navigate(['/checkout/payment']);
  }
}
