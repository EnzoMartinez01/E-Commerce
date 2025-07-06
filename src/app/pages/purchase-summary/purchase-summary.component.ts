import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AddressesService } from '../../core/services/users/addresses.service';
import { AuthService } from '../../core/services/auth/auth.service';
import {FormsModule} from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { Card } from 'primeng/card';
import { Panel } from 'primeng/panel';
import { RadioButtonModule } from 'primeng/radiobutton';
import {Button} from 'primeng/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-purchase-summary',
  standalone: true,
  imports: [CommonModule,FormsModule, DropdownModule, Card, Panel, RadioButtonModule, Button],
  templateUrl: './purchase-summary.component.html',
  styleUrl: './purchase-summary.component.css'
})
export class PurchaseSummaryComponent implements OnInit {
  tipoEntrega: string = 'domicilio';
  selectedAddress: any = null;

  cartItems: any[] = [];
  subtotal: number = 0;
  addresses: any[] = [];

  shippingCost = 20;

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras?.state as any;

    if (state?.cartItems && state?.subtotal !== undefined) {
      this.cartItems = state.cartItems;
      this.subtotal = state.subtotal;
    } else {
      const backup = JSON.parse(localStorage.getItem('purchaseSummary') || '{}');
      this.cartItems = backup.cartItems || [];
      this.subtotal = backup.subtotal || 0;
    }

    this.loadUserAddresses();
  }

  loadUserAddresses(): void {
    this.authService.getUserInfoFromToken().subscribe({
      next: (user) => {
        this.addresses = user.addresses || [];
      },
      error: (err) => console.error('Error al obtener direcciones desde el usuario', err)
    });
  }

  confirmarCompra() {
    if (this.tipoEntrega === 'domicilio' && !this.selectedAddress) {
      alert('Por favor selecciona una dirección para el envío.');
      return;
    }

    const order = {
      items: this.cartItems,
      deliveryType: this.tipoEntrega,
      address: this.tipoEntrega === 'domicilio' ? this.selectedAddress : null,
      subtotal: this.subtotal,
      shippingCost: this.tipoEntrega === 'domicilio' ? 20 : 0,
      total: this.tipoEntrega === 'domicilio' ? this.subtotal + 20 : this.subtotal
    };

    console.log(' Orden lista para continuar a Pago:', order);
  }

}
