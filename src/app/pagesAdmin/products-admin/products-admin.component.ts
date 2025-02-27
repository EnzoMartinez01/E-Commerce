import {Component, OnInit} from '@angular/core';
import {TableModule} from 'primeng/table';
import {Button} from 'primeng/button';
import {Tag} from 'primeng/tag';
import {Products} from '../../Models/products.model';
import {ProductsResponse, ProductsService} from '../../core/services/products/products.service';
import {map} from 'rxjs';
import {CurrencyPipe, NgClass, NgIf} from '@angular/common';
import {Ripple} from 'primeng/ripple';
import {Dialog} from 'primeng/dialog';
import {InputText} from 'primeng/inputtext';
import {FormsModule} from '@angular/forms';
import {DropdownModule} from 'primeng/dropdown';
import {Categories} from '../../Models/categories.model';
import {Brands} from '../../Models/brands.model';
import {CategoriesService} from '../../core/services/products/categories.service';
import {BrandsService} from '../../core/services/products/brands.service';

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
    FormsModule,
    DropdownModule,
    NgIf
  ],
  templateUrl: './products-admin.component.html',
  styleUrl: './products-admin.component.css'
})
export class ProductsAdminComponent implements OnInit {
  products: Products[] = [];
  productsResponse!: ProductsResponse;
  categories: Categories[] = [];
  brands: Brands[] = [];


  editProductContent = {
    idProduct: 0,
    productName: '',
    productDescription: '',
    sku: '',
    price: 0,
    price_creditcard: 0,
    quantity: 0,
    product_image: '',
    stock: 0,
    offerDescount: 0,
    priceOffer: 0,
    pdfFile: '',
    brand: 0,
    isOffer: true,
    isActive: true,
    category: 0
  }

  visibleDialog: boolean = false;
  deactivateDialog: boolean = false;
  viewDialog: boolean = false;
  selectedProduct: Products = {} as Products;

  constructor(private productService: ProductsService,
              private categoryService: CategoriesService,
              private brandService: BrandsService) {}

  ngOnInit() {
    this.loadProducts();
    this.loadCategories();
    this.loadBrands();
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
      .pipe(map((res) => res.content))
      .subscribe((data) => {
        this.products = data;
      });
  }

  loadCategories(): void {
    this.categoryService.getCategories(0, 10).subscribe(
      (data) => {
        this.categories = data.content.map((category: Categories) => ({
          idCategory: category.idCategory,
          categoryName: category.categoryName,
          categoryImage: category.categoryImage,
          isActive: category.isActive
        }));
      },
      (error) => {
        console.error('Error al cargar categorías:', error);
      }
    );
  }



  loadBrands(): void {
    this.brandService.getAllBrands(0, 10).subscribe(
      (data) => {
        this.brands = data.content.map((brand: { brandName: any; idBrand: any; }) => ({
          name: brand.brandName,
          idBrand: brand.idBrand
        }));
      },
      (error) => {
        console.error('Error al cargar marcas:', error);
      }
    );
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
    console.log("Producto recibido:", product);

    this.editProductContent = {
      idProduct: product.idProduct,
      productName: product.productName,
      productDescription: product.productDescription,
      sku: product.productSku,
      price: product.productPrice,
      price_creditcard: product.priceCreditCard,
      quantity: product.quantity,
      product_image: product.productImg,
      stock: product.stock,
      offerDescount: product.productOfferDiscount,
      priceOffer: product.priceOffer,
      pdfFile: product.pdfFile,
      brand: typeof product.idBrand === 'object' ? (product.idBrand as any).idBrand ?? 0 : product.idBrand ?? 0,
      isOffer: product.isOffer,
      isActive: product.isActive,
      category: typeof product.idCategory === 'object' ? (product.idCategory as any).idCategory ?? 0 : product.idCategory ?? 0
    };

    console.log("Producto preparado para editar:", this.editProductContent);
    this.visibleDialog = true;
  }


  saveProduct() {
    const updatedProduct = {
      ...this.editProductContent,
      price: this.editProductContent.price,
      category: this.editProductContent.category,
      brand: this.editProductContent.brand
    };

    console.log('Producto antes de actualizar:', updatedProduct);

    this.productService.updateProduct(updatedProduct.idProduct, updatedProduct).subscribe(() => {
      console.log('Producto actualizado');
      this.visibleDialog = false;
      this.loadProducts();
    });
  }


  deactivateProduct(product: Products) {
    this.selectedProduct = { ...product };
    this.deactivateDialog = true;
  }

  // Desactivate Products
  deleteProduct() {
    const deactivateProduct = {
      ...this.selectedProduct
    };

    this.productService.deactivateProduct(this.selectedProduct.idProduct).subscribe(() => {
      console.log('Producto desactivado');
      this.deactivateDialog = false;
      this.loadProducts();
    })
  }

  //View Producto
  viewProduct(product: Products) {
    this.selectedProduct = { ...product };
    this.viewDialog = true;

    this.productService.getProductsById(this.selectedProduct.idProduct).subscribe(
      (data) => {
        console.log('Producto obtenido:', data);
      },
      (error) => {
        console.error('Error al obtener el producto:', error);
      }
    );
  }


}
