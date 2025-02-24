import { Component } from '@angular/core';
import { ProductsFilterComponent } from "../products-filter/products-filter.component";
import { ProductsCardComponent } from "../products-card/products-card.component";

@Component({
  selector: 'app-products-info',
  imports: [ProductsFilterComponent, ProductsCardComponent],
  templateUrl: './products-info.component.html',
  styleUrl: './products-info.component.css'
})
export class ProductsInfoComponent {

}
