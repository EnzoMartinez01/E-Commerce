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

  //Edita
  editCategory(category: Categories) {
    this.selectedCategory = { ...category };
    this.visibleDialog = true;
  }

  saveCategory() {
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

    //Borra
    deleteCategory(categories: Categories) {
        console.log('Categorias eliminado');
      }


}
