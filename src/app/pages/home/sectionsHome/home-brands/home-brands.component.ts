import { Component } from '@angular/core';
import { CarouselModule } from 'primeng/carousel';
import { NgFor } from '@angular/common';


@Component({
  selector: 'app-home-brands',
  imports: [CarouselModule, NgFor],
  templateUrl: './home-brands.component.html',
  styleUrl: './home-brands.component.css'
})
export class HomeBrandsComponent {
  images = [
    'https://i.imgur.com/MBOUEVL.png',
    'https://i.imgur.com/MBOUEVL.png',
    'https://i.imgur.com/MBOUEVL.png',
    'https://i.imgur.com/MBOUEVL.png'
  ];

  responsiveOptions = [
    { breakpoint: '1024px', numVisible: 1, numScroll: 1 },
    { breakpoint: '768px', numVisible: 1, numScroll: 1 },
    { breakpoint: '560px', numVisible: 1, numScroll: 1 }
  ];

}
