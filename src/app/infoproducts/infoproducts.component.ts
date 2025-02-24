import { Component } from '@angular/core';
import { InfoNameComponent } from './sectionsInfo/info-name/info-name.component';
import { InfoTableComponent } from './sectionsInfo/info-table/info-table.component';

@Component({
  selector: 'app-infoproducts',
  imports: [InfoNameComponent,InfoTableComponent],
  templateUrl: './infoproducts.component.html',
  styleUrl: './infoproducts.component.css'
})
export class InfoproductsComponent {

}
