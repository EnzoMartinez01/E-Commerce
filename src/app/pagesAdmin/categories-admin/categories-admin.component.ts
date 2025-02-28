import { Component, OnInit } from '@angular/core';
import { CategoriesService, CategoryResponse  } from '../../core/services/products/categories.service';
import { Categories } from '../../Models/categories.model';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { CommonModule } from '@angular/common';
import { map } from 'rxjs';
import { FormsModule } from '@angular/forms';
import {Ripple} from 'primeng/ripple';
import {InputText} from 'primeng/inputtext';
import { NgClass} from '@angular/common';
import { ProductsService } from '../../core/services/products/products.service';


@Component({
  selector: 'app-categories-admin',
  imports: [ButtonModule,TableModule,DialogModule,CommonModule,FormsModule,Ripple,InputText,NgClass],
  templateUrl: './categories-admin.component.html',
  styleUrl: './categories-admin.component.css'
})
export class CategoriesAdminComponent {
  categories: Categories[] = [];
  selectedCategory: Categories = {} as Categories;
  visibleDialog:boolean = false;
  addDialog: boolean = false;
  viewDialog: boolean = false;
  deactivateDialog: boolean = false;

  addCategoryContent = {
    categoryName: '',
    categoryImage: ''
  }


  constructor(private categoriesService: CategoriesService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categoriesService.getCategories(0, 10)
      .pipe(map((res) => res.content))
            .subscribe((data) => {
              this.categories = data;
            });
        }


    //Add Product
   addCategory() {
  this.addCategoryContent = {
    categoryName: '',
    categoryImage: '',
   
  }
  this.addDialog = true;
}

saveCategory() {
  this.categoriesService.addCategory(this.addCategoryContent).subscribe(
    (response) => {
      console.log('Producto agregado:', response);
      this.loadCategories();
      this.addDialog = false;
    },
    (error) => {
      console.error('Error al agregar producto:', error);
    }
  );
}


  //Edita
  editCategory(category: Categories) {
    console.log('Categoria recibida', category);
    this.selectedCategory = { ...category };
    this.visibleDialog = true;
  }

  updateCategory() {
    const updatedCategory = {
      ...this.selectedCategory,
      idCategory: this.selectedCategory.idCategory,
    };

    console.log('Categoria antes de actualizar:', this.selectedCategory);

    this.categoriesService.updateCategory(this.selectedCategory.idCategory, updatedCategory).subscribe(() => {
      console.log('Categoria actualizado');
      this.visibleDialog = false;
      this.loadCategories();
    })
    }

    deactivateCategory(categories: Categories) {
      console.log('Categoria recibida', categories);
        this.selectedCategory = { ...categories };
        this.deactivateDialog = true;
      }

    // Desactivate Categories

    deleteCategory() {
      const deactivateCategory = {
        ...this.selectedCategory
      };
  
      this.categoriesService.deactivateCategory(this.selectedCategory.idCategory).subscribe(() => {
        console.log('Producto desactivado');
        this.deactivateDialog = false;
        this.loadCategories();
      })
    }


}
