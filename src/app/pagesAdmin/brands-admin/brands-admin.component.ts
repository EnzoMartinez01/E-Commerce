import { Component } from '@angular/core';
import { Brands } from '../../Models/brands.model';
import { BrandsService } from '../../core/services/products/brands.service';
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
  selector: 'app-brands-admin',
  imports: [ButtonModule,TableModule,DialogModule,CommonModule,FormsModule,Ripple,InputText,NgClass],
  templateUrl: './brands-admin.component.html',
  styleUrl: './brands-admin.component.css'
})
export class BrandsAdminComponent {
  brands: Brands[] = [];
    selectedBrand: Brands = {} as Brands;
    visibleDialog:boolean = false;
  
  
    constructor(private brandsService: BrandsService) {}
  
    ngOnInit(): void {
      this.loadBrand();
    }
  
    loadBrand(): void {
      this.brandsService.getBrands(0, 10)
        .pipe(map((res) => res.content))
              .subscribe((data) => {
                this.brands = data;
              });
          }
  
    //Edita
    editBrand(brand: Brands) {
      this.selectedBrand = { ...brand };
      this.visibleDialog = true;
    }
  
    saveBrand() {
      const updatedBrand = {
        ...this.selectedBrand,
        idBrand: this.selectedBrand.idBrand,
      };
  
      console.log('Marcas antes de actualizar:', this.selectedBrand);
  
      this.brandsService.updateBrand(this.selectedBrand.idBrand, updatedBrand).subscribe(() => {
        console.log('Marca actualizada');
        this.visibleDialog = false;
        this.loadBrand();
      })
      }
  
      //Borra
      deleteBrand(brands: Brands) {
          console.log('Marca eliminada');
        }
  

}
