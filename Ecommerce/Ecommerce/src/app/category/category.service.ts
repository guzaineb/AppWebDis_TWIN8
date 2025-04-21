import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../models/category';
import { Produit } from '../models/produit';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = 'http://localhost:8081/Produit/api';

  constructor(private http: HttpClient) {}

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiUrl}/categories/all-categories`);
  }

  createCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(`${this.apiUrl}/categories/add-category`, category);
  }

  getProduitsByCategory(id: number): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/categories/${id}/produits`);
  }

  getQRCodeForCategory(categoryId: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/qrcode/category/${categoryId}`, {
      responseType: 'blob'
    });
  }
  
}