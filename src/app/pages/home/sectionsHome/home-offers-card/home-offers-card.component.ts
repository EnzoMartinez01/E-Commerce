import { Component } from '@angular/core';
import { CarouselModule } from 'primeng/carousel';
import { ButtonModule } from 'primeng/button';
import { TagModule } from 'primeng/tag';
import {ProductsService} from '../../../../core/services/products/products.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-home-offers-card',
  imports: [CarouselModule, ButtonModule, TagModule],
  templateUrl: './home-offers-card.component.html',
  styleUrl: './home-offers-card.component.css'
})
export class HomeOffersCardComponent {
  filters = {
    brand: null,
    category: null,
    price: null,
    stock: null,
    isOffer: true,
    isActive: null,
    page: 0,
    size: 10,
    searchTerms: null
  }

  totalElements: number = 0;
  pageSize: number = 10;

  products: {
    name: string;
    price: number;
    image: string;
    offer: number;
    priceOffer: number;
  }[] = [];


  responsiveOptions = [
    { breakpoint: '1024px', numVisible: 3, numScroll: 1 },
    { breakpoint: '768px', numVisible: 2, numScroll: 1 },
    { breakpoint: '560px', numVisible: 1, numScroll: 1 }
  ];

  constructor(private productsService: ProductsService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    const { brand, category, price, stock, isOffer, isActive, page, size, searchTerms } = this.filters;

    this.productsService.getProductsFilter(
      brand, category, price, stock, null, null, isOffer, isActive, page, size, searchTerms
    ).subscribe((data) => {
      if (data && data.content) {
        this.products = data.content.map((product: any) => ({
          name: product.productName,
          price: product.productPrice,
          image: product.productImg,
          offer: product.productOfferDiscount,
          category: product.categoryName,
          priceOffer: product.priceOffer
        }));
        this.totalElements = data.totalElements;
      }
    }, (error) => {
      console.error('Error al cargar productos:', error);
    });
  }


}



