import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl = 'http://localhost:8080/api/v1/cart';

  constructor(private http: HttpClient) {}

  addToCart(idProduct: number, quantity: number): Observable<any> {
    const token = sessionStorage.getItem('authToken');

    if (!token) {
      const localCart = JSON.parse(localStorage.getItem('localCart') || '[]');
      const index = localCart.findIndex((item: any) => item.idProduct === idProduct);

      if (index !== -1) {
        localCart[index].quantity += quantity;
      } else {
        localCart.push({ idProduct, quantity });
      }

      localStorage.setItem('localCart', JSON.stringify(localCart));

      return of({ message: 'Producto agregado al carrito local (sin login)' });
    }

    const headers = new HttpHeaders({ 'Authorization': `Bearer ${token}` });
    const params = new HttpParams()
      .set('idProduct', idProduct.toString())
      .set('quantity', quantity.toString())
      .set('paymentMethod', '2');

    return this.http.post<any>(`${this.baseUrl}/addProductToCart`, null, { headers, params });
  }

  getCartByUser(idUser: number, page: number = 0, size: number = 10): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) throw new Error('Token no encontrado');

    const headers = new HttpHeaders({ 'Authorization': `Bearer ${token}` });
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<any>(`${this.baseUrl}/getCartByUser/${idUser}`, { headers, params });
  }

  getCartSummaryByUser(idUser: number): Observable<{ idCart: number, delivery: number, total: number } | null> {
    return this.getCartByUser(idUser).pipe(
      map((res) => {
        console.log('Respuesta del backend en getCartByUser:', res);
        if (res?.content?.length > 0) {
          const cart = res.content[0];
          return {
            idCart: cart.idCart,
            delivery: cart.delivery ?? 0,
            total: cart.total ?? 0
          };
        }
        return null;
      })
    );
  }

  updateCartItemQuantity(idCartItem: number, quantity: number): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) throw new Error('Token no encontrado');

    const headers = new HttpHeaders({ 'Authorization': `Bearer ${token}` });
    const params = new HttpParams()
      .set('idCartItem', idCartItem.toString())
      .set('quantity', quantity.toString());

    return this.http.put<any>(`${this.baseUrl}/updateQuantity`, null, { headers, params });
  }

  deleteCartItem(idCartItem: number): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    if (!token) throw new Error('Token no encontrado');

    const headers = new HttpHeaders({ 'Authorization': `Bearer ${token}` });
    return this.http.delete(`${this.baseUrl}/items/${idCartItem}`, { headers });
  }

  migrateLocalCartToBackend(): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    const idUser = sessionStorage.getItem('idUser');
    const localCart = JSON.parse(localStorage.getItem('localCart') || '[]');

    if (!token || !idUser || localCart.length === 0) {
      return of(null);
    }

    const headers = new HttpHeaders({ 'Authorization': `Bearer ${token}` });

    const requests = localCart.map((item: any) => {
      const params = new HttpParams()
        .set('idProduct', item.idProduct)
        .set('quantity', item.quantity)
        .set('paymentMethod', '2');

      return this.http.post(`${this.baseUrl}/addProductToCart`, null, { headers, params });
    });

    return forkJoin(requests).pipe(
      map((res) => {
        localStorage.removeItem('localCart');
        return res;
      })
    );
  }
}
