import {Component, OnInit} from '@angular/core';
import {TableModule} from 'primeng/table';
import {Button} from 'primeng/button';
import {Tag} from 'primeng/tag';
import {Products} from '../../Models/products.model';
import {ProductsResponse, ProductsService} from '../../core/services/products/products.service';
import {map} from 'rxjs';
import {CurrencyPipe, NgClass} from '@angular/common';
import {Ripple} from 'primeng/ripple';
import {Dialog} from 'primeng/dialog';
import {InputText} from 'primeng/inputtext';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-products-admin',
  imports: [
    TableModule,
    Button,
    Tag,
    CurrencyPipe,
    NgClass,
    Ripple,
    Dialog,
    InputText,
    FormsModule
  ],
  templateUrl: './products-admin.component.html',
  styleUrl: './products-admin.component.css'
})
export class ProductsAdminComponent implements OnInit {
  products: Products[] = [];
  productsResponse!: ProductsResponse;
  visibleDialog: boolean = false;
  selectedProduct: Products = {} as Products;

  constructor(private productService: ProductsService) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.productService
      .getProductsFilter(
        null, // brandId
        null, // categoryId
        null, // subCategoryId
        null, // attributeIds
        null, // price
        null, // stock
        null, // isOffer
        true, // isActive
        0, // page
        10, // size
        null // searchTerms
      )
      .pipe(map((res) => res.content)) // Transforma la respuesta para obtener solo los productos
      .subscribe((data) => {
        this.products = data;
      });
  }

  getSeverity(stock: number) {
    if (stock > 3) {
      return 'success';
    } else if (stock > 0 && stock <= 3) {
      return 'warn';
    } else {
      return 'danger';
    }
  }

  //EditProducts
  editProduct(product: Products) {
    this.selectedProduct = { ...product };
    this.visibleDialog = true;
  }


  saveProduct() {
    const updatedProduct = {
      ...this.selectedProduct,
      price: this.selectedProduct.productPrice,
    };

    console.log('Producto antes de actualizar:', this.selectedProduct);
 
    this.productService.updateProduct(this.selectedProduct.idProduct, updatedProduct).subscribe(() => {
      console.log('Producto actualizado');
      this.visibleDialog = false;
      this.loadProducts();
    })
  }

  // Desactivate Products
  deleteProduct(product: Products) {
    console.log('Producto eliminado');
  }

  //View Producto
  viewProduct(product: Products) {
    console.log('Viendo detalles de:', product);
  }

}
