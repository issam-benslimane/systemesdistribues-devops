import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user',
  template: `
    <div>
      <h2>User Page</h2>
      <p>{{ message }}</p>
    </div>
  `
})
export class UserComponent implements OnInit {
  message = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get('http://localhost:8081/api/private/user', { responseType: 'text' })
      .subscribe({
        next: (response) => this.message = response,
        error: (error) => this.message = 'Error: ' + error.message
      });
  }
} 