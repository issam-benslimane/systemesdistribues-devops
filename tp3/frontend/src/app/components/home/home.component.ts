import { Component } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-home',
  template: `
    <div>
      <h1>Welcome to E-Bank</h1>
      <button *ngIf="!isLoggedIn" (click)="login()">Login</button>
      <button *ngIf="isLoggedIn" (click)="logout()">Logout</button>
      <div *ngIf="isLoggedIn">
        <p>Welcome {{ username }}</p>
        <a routerLink="/user" *ngIf="isUser">User Page</a>
        <a routerLink="/admin" *ngIf="isAdmin">Admin Page</a>
      </div>
    </div>
  `
})
export class HomeComponent {
  isLoggedIn = false;
  username = '';
  isUser = false;
  isAdmin = false;

  constructor(private keycloak: KeycloakService) {
    this.initializeUserInfo();
  }

  async initializeUserInfo() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();
    if (this.isLoggedIn) {
      const userProfile = await this.keycloak.loadUserProfile();
      this.username = userProfile.username || '';
      const roles = await this.keycloak.getUserRoles();
      this.isUser = roles.includes('USER');
      this.isAdmin = roles.includes('ADMIN');
    }
  }

  login() {
    this.keycloak.login();
  }

  logout() {
    this.keycloak.logout();
  }
} 