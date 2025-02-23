import { Injectable } from '@angular/core';
import { Products } from '../../../Models/products.model';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ProductsResponse {
  content: Products[];
  totalElements: number;
}

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private baseUrl = 'http://localhost:8080/api/v1/products';

  constructor(private http: HttpClient) { }

  // Products
  getProductsFilter(
    brand: number | null,
    category: number | null,
    price: number | null,
    stock: number | null,
    isOffer: boolean | null,
    isActive: boolean | null,
    page: number,
    size: number,
    searchTerms: string | null
  ): Observable<ProductsResponse> {
    const token = sessionStorage.getItem('authToken');

    if (!token) {
      throw new Error('Token not found.');
    }
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    if (brand !== null && brand !== undefined) {
      params = params.set('brand', brand.toString());
    }

    if (category !== null && category !== undefined) {
      params = params.set('category', category.toString());
    }

    if (price !== null && price !== undefined) {
      params = params.set('price', price.toString());
    }

    if (stock !== null && stock !== undefined) {
      params = params.set('stock', stock.toString());
    }

    if (isOffer !== null && isOffer !== undefined) {
      params = params.set('isOffer', isOffer.toString());
    }

    if (isActive !== null && isActive !== undefined) {
      params = params.set('isActive', isActive.toString());
    }

    if (searchTerms !== null && searchTerms !== undefined) {
      params = params.set('searchTerms', searchTerms);
    }

    return this.http.get<any>(`${this.baseUrl}/getProductsByFilters`, { headers, params });
  }
}
