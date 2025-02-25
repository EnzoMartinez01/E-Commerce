import {Component, Input, OnInit} from '@angular/core';
import { TabsModule } from 'primeng/tabs';
import { TableModule } from 'primeng/table';
import {CharacteristicsService} from '../../../core/services/products/characteristics.service';
import {AttributesService} from '../../../core/services/products/attributes.service';


@Component({
  selector: 'app-info-table',
  imports: [TabsModule,TableModule],
  templateUrl: './info-table.component.html',
  styleUrl: './info-table.component.css'
})
export class InfoTableComponent implements OnInit{
  @Input({ required: true }) idProduct: number | null = null;
  characteristics: any[] = [];
  attributes: any[] = [];

  constructor(private characteristicsService: CharacteristicsService,
              private attributesService: AttributesService) {}

  ngOnInit(): void {
    console.log("🛠️ idProduct recibido en InfoTableComponent:", this.idProduct);

    if (this.idProduct) {
      this.loadCharacteristics(this.idProduct);
      this.loadAttributes(this.idProduct);
    } else {
      console.warn("⚠️ No se recibió idProduct, no se cargan datos.");
    }
  }


  loadCharacteristics(idProduct: number): void {
    this.characteristicsService.getCharacteristics(idProduct, 0, 10).subscribe(
      (data) => {
        console.log('✅ Características obtenidas:', data);

        if (data && data.content) {
          console.log('📌 Características procesadas:', data.content);
          this.characteristics = data.content;
        } else {
          console.warn('⚠ Características vacías o mal formateadas:', data);
          this.characteristics = [];
        }
      },
      (error) => {
        console.error('❌ Error al cargar características:', error);
      }
    );
  }

  loadAttributes(idProduct: number): void {
    this.attributesService.getAttributes(idProduct, 0, 10).subscribe(
      (data) => {
        console.log("✅ Atributos recibidos:", data);

        if (data && data.content) {
          console.log("📌 Atributos procesados:", data.content);
          this.attributes = data.content;
        } else {
          console.warn("⚠ Atributos vacíos o mal formateados:", data);
          this.attributes = [];
        }
      },
      (error) => console.error('❌ Error al cargar atributos:', error)
    );
  }

}
