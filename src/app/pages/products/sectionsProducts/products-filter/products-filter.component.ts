import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DividerModule } from 'primeng/divider';
import {NgForOf} from '@angular/common';




@Component({
  selector: 'app-products-filter',
  imports: [FormsModule,CommonModule,DividerModule,NgForOf],
  templateUrl: './products-filter.component.html',
  styleUrl: './products-filter.component.css'
})
export class ProductsFilterComponent {
  filtros = [
    {
      nombre: 'Categorías',
      abierto: false,
      opciones: [
        { nombre: 'Cajas', seleccionado: false },
        { nombre: 'Metal', seleccionado: false },
        { nombre: 'Poliéster', seleccionado: false }
      ]
    },
    {
      nombre: 'Tipo',
      abierto: false,
      opciones: [
        { nombre: 'Tipo 1', seleccionado: false },
        { nombre: 'Tipo 2', seleccionado: false }
      ]
    },
    {
      nombre: 'Marca',
      abierto: false,
      opciones: [
        { nombre: 'Marca 1', seleccionado: false },
        { nombre: 'Marca 2', seleccionado: false }
      ]
    },
    {
      nombre: 'Voltaje',
      abierto: false,
      opciones: [
        { nombre: '110V', seleccionado: false },
        { nombre: '220V', seleccionado: false }
      ]
    },
    {
      nombre: 'Amperaje',
      abierto: false,
      opciones: [
        { nombre: '10A', seleccionado: false },
        { nombre: '20A', seleccionado: false }
      ]
    },
    {
      nombre: 'Medidas',
      abierto: false,
      opciones: [
        { nombre: 'Pequeño', seleccionado: false },
        { nombre: 'Grande', seleccionado: false }
      ]
    },
    {
      nombre: 'Color',
      abierto: false,
      opciones: [
        { nombre: 'Blanco', seleccionado: false },
        { nombre: 'Negro', seleccionado: false }
      ]
    },
    {
      nombre: 'Material',
      abierto: false,
      opciones: [
        { nombre: 'Plástico', seleccionado: false },
        { nombre: 'Metal', seleccionado: false }
      ]
    }
  ];

  toggleFiltro(filtro: any) {
    filtro.abierto = !filtro.abierto;
  }

}


