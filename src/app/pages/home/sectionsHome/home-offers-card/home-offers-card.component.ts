import { Component } from '@angular/core';
import { CarouselModule } from 'primeng/carousel';
import { ButtonModule } from 'primeng/button';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-home-offers-card',
  imports: [CarouselModule, ButtonModule, TagModule],
  templateUrl: './home-offers-card.component.html',
  styleUrl: './home-offers-card.component.css'
})
export class HomeOffersCardComponent {
  products = [
    { name: 'Producto 1', price: 100, image: 'bamboo-watch.jpg'},
    { name: 'Producto 2', price: 150, image: 'blue-t-shirt.jpg'},
    { name: 'Producto 3', price: 200, image: 'gaming-set.jpg'},
    { name: 'Producto 1', price: 100, image: 'bamboo-watch.jpg'},
    { name: 'Producto 2', price: 150, image: 'blue-t-shirt.jpg'},
    { name: 'Producto 3', price: 200, image: 'gaming-set.jpg'},
    { name: 'Producto 1', price: 100, image: 'bamboo-watch.jpg'},
    { name: 'Producto 2', price: 150, image: 'blue-t-shirt.jpg'},
    { name: 'Producto 3', price: 200, image: 'gaming-set.jpg'},
    { name: 'Producto 1', price: 100, image: 'bamboo-watch.jpg'},
    { name: 'Producto 2', price: 150, image: 'blue-t-shirt.jpg'},
    { name: 'Producto 3', price: 200, image: 'gaming-set.jpg'}
    
  ];

  responsiveOptions = [
    { breakpoint: '1024px', numVisible: 3, numScroll: 1 },
    { breakpoint: '768px', numVisible: 2, numScroll: 1 },
    { breakpoint: '560px', numVisible: 1, numScroll: 1 }
  ];
  
}
  


