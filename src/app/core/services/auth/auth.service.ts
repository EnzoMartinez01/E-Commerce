import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  username: string | null = null;
  private isAuthenticated = false;

  private apiBaseUrl = 'http://localhost:8080/api/v1/auth';

  constructor( private http: HttpClient ) { }

  registerUser(user: any, idRole: number): Observable<any> {
    return this.http.post(`${this.apiBaseUrl}/register/user/${idRole}`, user).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    console.error("Error en la API:", error);
    return throwError(() => new Error('Error al registrar usuario.'));
  }

  login (username: string, password: string): Observable<any> {
    return this.http.post(`${this.apiBaseUrl}/login`, { username, password }).pipe(
      map((response) => {
        this.isAuthenticated = true;
        return response;
      }),
      catchError((error) => {
        this.isAuthenticated = false;
        return throwError(() => error);
      })
    );
  }

  logout(): Observable<any> {
    const token = sessionStorage.getItem('authToken');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
    return this.http.post(`${this.apiBaseUrl}/logout`, {}, { headers });
  }

  saveToken(token: string): void {
    sessionStorage.setItem('authToken', token);
  }

  getToken(): string | null {
    return sessionStorage.getItem('authToken');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
