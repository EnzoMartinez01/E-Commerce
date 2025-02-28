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
  imports: [ButtonModule,TableModule,DialogModule,CommonModule,FormsModule,Ripple,InputText],
  templateUrl: './faq-admin.component.html',
  styleUrl: './faq-admin.component.css'
})
export class FaqAdminComponent {
  faqs: any[] = [];
  selectedFaqs: any = {};
  visibleDialog:boolean = false;
  editFaq = {
    idFaq: 0,
    question: '',
    answer: ''
  };



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
  editFaqs(faqs: any) {
    this.selectedFaqs = { ...faqs };
    this.editFaq = {
      idFaq: faqs.idFaq,
      question: faqs.questionFaq,
      answer: faqs.answerFaq
    };
    console.log('FAQ editado:', this.editFaq);
    this.visibleDialog = true;
  }


  saveFaqs() {
    const updatedFaqs = {
      ...this.editFaq,
    };

    console.log('FAQ antes de actualizar:', updatedFaqs);

    this.faqService.updateFaqs(
      updatedFaqs.idFaq,
      updatedFaqs
    ).subscribe(() => {
        console.log('FAQ actualizado');
        this.visibleDialog = false;
        this.loadFaqs();
      },
      (error) => {
        console.error('Error al actualizar el FAQ:', error);
      }
    );
  }


  //Borra
      deleteFaqs(faq: Faq) {
          console.log('Marcas eliminado');
        }


}
