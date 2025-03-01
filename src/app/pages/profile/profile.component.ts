import { Component } from '@angular/core';
import {AuthService} from '../../core/services/auth/auth.service';
import {Card} from 'primeng/card';
import {Button} from 'primeng/button';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-profile',
  imports: [
    Card,
    Button,
    NgClass
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  selectedUser: any = {};
  userInitial: string = "U";

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.loadUser();
    this.userInitial = this.getUserInitial();
  }

  loadUser(): void {
    const token = this.authService.getToken();
    if (token) {
      this.authService.getUserInfoFromToken().subscribe(
        (data) => {
          console.log('Información del usuario:', data);
          this.selectedUser = data;
        },
        (error) => {
          console.error('Error al obtener el usuario:', error);
        }
      );
    }
  }

  getUserInitial(): string {
    const token = this.authService.getToken();
    if (token) {
      const userName = this.authService.getUsernameFromToken(token) || "U";
      return userName.charAt(0).toUpperCase();
    }
    return "U";
  }
}
