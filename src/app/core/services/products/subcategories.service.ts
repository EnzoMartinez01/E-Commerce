import {Injectable} from '@angular/core';
import {Subcategories} from '../../../Models/subcategories.model';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface SubcategoriesResponse {
  content: Subcategories[];
  totalElements: number;
}

@Injectable({
  providedIn: 'root'
})

export class SubcategoriesService {

  private baseUrl = 'http://localhost:8080/api/v1/subcategories';

  constructor(private http: HttpClient) {}

  // Get Subcategories
  getSubcategories(
    searchTerm: string | null,
    price: number | null,
    stock: number | null,
    isOffer: boolean | null,
    brandId: number | null,
    categoryId: number | null,
    attributesIds: number[] | null,
    isActive: boolean | null
  ): Observable<SubcategoriesResponse> {
    const token = sessionStorage.getItem('authToken');

    if (!token) {
      throw new Error('Token not found.');
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    let params = new HttpParams()

    if (searchTerm !== null) {
      params = params.set('searchTerm', searchTerm.toString());
    }

    if (price !== null) {
      params = params.set('price', price.toString());
    }

    if (isOffer !== null) {
      params = params.set('isOffer', isOffer.toString());
    }

    if (stock !== null) {
      params = params.set('stock', stock.toString());
    }


    if (brandId !== null) {
      params = params.set('brandId', brandId.toString());
    }

    if (categoryId !== null) {
      params = params.set('categoryId', categoryId.toString());
    }

    if (attributesIds !== null && attributesIds.length > 0) {
      params = params.set('attributesIds', attributesIds.join(','));
    }

    if (isActive !== null) {
      params = params.set('isActive', isActive.toString());
    }

    return this.http.get<any>(`${this.baseUrl}/products/subcategories`, { headers, params });
  }
}
