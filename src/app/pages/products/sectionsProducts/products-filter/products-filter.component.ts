import { Component, HostListener, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DividerModule } from 'primeng/divider';
import {NgForOf} from '@angular/common';
import {ProductsService} from '../../../../core/services/products/products.service';
import {Paginator} from 'primeng/paginator';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {CategoriesService} from '../../../../core/services/products/categories.service';


@Component({
  selector: 'app-products-filter',
  imports: [FormsModule, CommonModule, DividerModule, NgForOf, Paginator, RouterLink],
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

  products: {
    idProduct: number,
    name: string;
    price: number;
    image: string;
    offer: number;
    priceOffer: number;
    category: string;
    isActive: boolean;
    isOffer: boolean;
  }[] = [];

  filters = {
    brandId: null as number | null,
    categoryId: 1,
    price: null as number | null,
    stock: null as number | null,
    isOffer: null as boolean | null,
    isActive: true,
    page: 0,
    size: 10,
    searchTerms: null as string | null
  }

  first: number = 0;
  rows: number = 8;
  totalRecords: number = 0;

  constructor(private productsService: ProductsService,
              private route: ActivatedRoute,
              private categoriesService: CategoriesService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const categoryName = params.get('categoryName');
      if (categoryName) {
        this.categoriesService.getCategoryByName(categoryName).subscribe(category => {

          if (category && category.idCategory) {
            this.filters.categoryId = category.idCategory;

            setTimeout(() => this.loadProducts(0, this.rows), 100);
          }
        });
      }
    });
  }



  loadProducts(page: number, size: number): void {
    const { brandId, categoryId, price, stock, isOffer, isActive, searchTerms } = this.filters;
    this.productsService.getProductsFilter(
      brandId, categoryId, price, stock, isOffer, isActive, page, size, searchTerms
    ).subscribe(
      (data) => {

        if (data && data.content) {
          this.products = data.content.map((product: any) => ({
            idProduct: product.idProduct,
            name: product.productName,
            price: product.productPrice,
            image: product.productImg,
            offer: product.productOfferDiscount,
            category: product.categoryName,
            priceOffer: product.priceOffer,
            isActive: product.isActive,
            isOffer: product.isOffer
          }));
          this.totalRecords = data.totalElements;
        } else {
          console.warn('⚠ La API devolvió una lista vacía.');
        }
      },
      (error) => {
        console.error('Error al cargar productos:', error);
      }
    );
  }


  onPageChange(event: any): void {
    this.first = event.first;
    this.rows = event.rows;
    const page = event.first / event.rows;
    this.loadProducts(page, this.rows);
  }

  toggleFiltro(filtro: any) {
    filtro.abierto = !filtro.abierto;
  }

  filtrosVisibles = false; 
  isDesktop = window.innerWidth > 768; 

  @HostListener('window:resize', ['$event'])
  onResize() {
    this.isDesktop = window.innerWidth > 768;
  }

  toggleFiltros() {
    this.filtrosVisibles = !this.filtrosVisibles;
    console.log('Estado de filtrosVisibles:', this.filtrosVisibles); 
  }

  ordenarProductos(event: Event) {
    
    const selectElement = event.target as HTMLSelectElement;
    const criterio = selectElement.value; 

    this.products.sort((a, b) => {
        const precioA = a.isOffer ? a.priceOffer : a.price;
        const precioB = b.isOffer ? b.priceOffer : b.price;

        return criterio === 'asc' ? precioA - precioB : precioB - precioA;
    });
}
}



