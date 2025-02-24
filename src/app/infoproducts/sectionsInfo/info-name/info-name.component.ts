import {Component, OnInit, TemplateRef} from '@angular/core';
import { ProductsService } from '../../../core/services/products/products.service';
import {ActivatedRoute} from '@angular/router';
import {CategoriesService} from '../../../core/services/products/categories.service';
import {NgForOf, NgIfContext} from '@angular/common';
import { CommonModule } from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-info-name',
  imports: [NgForOf, CommonModule, FormsModule],
  templateUrl: './info-name.component.html',
  styleUrl: './info-name.component.css'
})
export class InfoNameComponent implements OnInit{

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
    quantity: number;
  }[] = [];

  constructor(private productsService: ProductsService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productsService.getProductsById(1).subscribe(
      (data) => {
        console.log("Respuesta API:", data);

        if (!data) {
          console.warn("⚠ No se encontraron productos.");
          return;
        }

        if (Array.isArray(data)) {
          this.products = data.map((product: any) => this.mapProduct(product));

        } else if (data.content && Array.isArray(data.content)) {
          this.products = data.content.map((product: any) => this.mapProduct(product));
        } else {
          this.products = [this.mapProduct(data)];
        }
      },
      (error) => {
        console.error('Error al cargar productos:', error);
      }
    );
  }

  private mapProduct(product: any): any {
    return {
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
      priceOffer: product.priceOffer,
      quantity: 1
    };
  }

  incrementQuantity(product: any): void {
    product.stock++;
  }

  decrementQuantity(product: any): void {
    if (product.stock > 1) {
      product.stock--;
    }
  }

  addToCart(product: any): void {
    console.log(`🛒 Agregado al carrito: ${product.name} - Cantidad: ${product.stock}`);
  }
}
