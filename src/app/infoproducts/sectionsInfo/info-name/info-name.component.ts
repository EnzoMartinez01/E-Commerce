import { Component, TemplateRef } from '@angular/core';
import { ProductsService } from '../../../core/services/products/products.service';
import {ActivatedRoute} from '@angular/router';
import {CategoriesService} from '../../../core/services/products/categories.service';
import {NgForOf, NgIfContext} from '@angular/common';
import { CommonModule } from '@angular/common';

interface Product {
  name: string;
  description: string;
  price: number;
  priceCreditCard: number;
  brandImage: string;
  image: string;
  category: string;
  isActive: boolean;
  sku: string;
  isOffer: boolean;
  priceOffer: number;
}

@Component({
  selector: 'app-info-name',
  imports: [NgForOf, CommonModule],
  templateUrl: './info-name.component.html',
  styleUrl: './info-name.component.css'
})
export class InfoNameComponent {

  products: {
  name: string;
  description: string;
  price: number;
  priceCreditCard: number;
  image: string;
  brandImage: string;
  offer: number;
  sku: string;
  priceOffer: number;
  category: string;
  isActive: boolean;
  isOffer: boolean;
  }[] = [];;



  constructor(private productsService: ProductsService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productsService.getProductsById(1).subscribe(
      (data) => {
        if (data && data.content) {
          console.log("Producto Recibido", data)
          this.products = data.content.map((product: any) => ({
            name: product.productName,
            description: product.productDescription,
            price: product.productPrice,
            priceCreditCard: product.priceCreditCard,
            brandImage: product.brandImage,
            image: product.productImg,
            category: product.categoryName,
            isActive: product.isActive,
            sku: product.productSku,
            isOffer: product.isOffer,
            priceOffer: product.priceOffer
          }));
        } else {
          console.warn('⚠ La API devolvió una lista vacía.');
        }
      },
      (error) => {
        console.error('Error al cargar productos:', error);
      }
    );
  }
  }
