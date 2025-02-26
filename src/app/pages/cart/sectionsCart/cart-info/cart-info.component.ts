import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Producto {
  id: number;
  nombre: string;
  imagen: string;
  precio: number;
  cantidad: number;
}

@Component({
  selector: 'app-cart-info',
  imports: [CommonModule],
  templateUrl: './cart-info.component.html',
  styleUrl: './cart-info.component.css'
})
export class CartInfoComponent {
  productos: Producto[] = [
    {
      id: 1,
      nombre: 'JBL Charge 5 Wi-Fi',
      imagen: 'https://i.imgur.com/pW5kPqJ.jpg',
      precio: 1029.00,
      cantidad: 1,
    }
  ];

  get subtotal(): number {
    return this.productos.reduce((acc, producto) => acc + producto.precio * producto.cantidad, 0);
  }

  aumentarCantidad(producto: Producto): void {
    producto.cantidad++;
  }

  disminuirCantidad(producto: Producto): void {
    if (producto.cantidad > 1) {
      producto.cantidad--;
    }
  }

}
