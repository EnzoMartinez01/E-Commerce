import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { CommonModule } from '@angular/common';
import { map } from 'rxjs';
import { FormsModule } from '@angular/forms';
import {Ripple} from 'primeng/ripple';
import {InputText} from 'primeng/inputtext';
import { NgClass} from '@angular/common';
import { FaqService } from '../../core/services/faq/faq.service';
import { Faq } from '../../Models/faq.model';

@Component({
  selector: 'app-faq-admin',
  imports: [ButtonModule,TableModule,DialogModule,CommonModule,FormsModule,Ripple,InputText,NgClass],
  templateUrl: './faq-admin.component.html',
  styleUrl: './faq-admin.component.css'
})
export class FaqAdminComponent {
  faqs: Faq[] = [];
    selectedFaqs: Faq = {} as Faq;
    visibleDialog:boolean = false;


    constructor(private faqService: FaqService) {}

    ngOnInit(): void {
      this.loadFaqs();
    }

    loadFaqs(): void {
      this.faqService.getAllFaq(0, 10)
        .pipe(map((res) => res.content))
              .subscribe((data) => {
                this.faqs = data;
              });
          }

    //Edita
    editFaqs(faqs: Faq) {
      this.selectedFaqs = { ...faqs };
      this.visibleDialog = true;
    }

    saveFaqs() {
      const updatedFaqs = {
        ...this.selectedFaqs,
        idFaq: this.selectedFaqs.idFaq,
      };

      console.log('FAQ antes de actualizar:', this.selectedFaqs);

      this.faqService.updateFaqs(this.selectedFaqs.idFaq, updatedFaqs).subscribe(() => {
        console.log('FAQ actualizado');
        this.visibleDialog = false;
        this.loadFaqs();
      })
      }

      //Borra
      deleteFaqs(faq: Faq) {
          console.log('Marcas eliminado');
        }


}
