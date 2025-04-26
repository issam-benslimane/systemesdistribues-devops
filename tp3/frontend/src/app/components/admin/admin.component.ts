import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-admin',
  template: `
    <div>
      <h2>Admin Page</h2>
      <p>{{ message }}</p>
    </div>
  `
})
export class AdminComponent implements OnInit {
  message = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get('http://localhost:8081/api/private/admin', { responseType: 'text' })
      .subscribe({
        next: (response) => this.message = response,
        error: (error) => this.message = 'Error: ' + error.message
      });
  }
} 