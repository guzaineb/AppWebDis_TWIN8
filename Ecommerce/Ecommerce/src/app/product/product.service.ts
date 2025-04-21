import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produit } from '../models/produit';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {
  private apiUrl = 'http://localhost:8081/Produit/produits';

  constructor(private http: HttpClient) {}

  getAllProduits(): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/all`);
  }

  createProduit(formData: FormData): Observable<Produit> {
    return this.http.post<Produit>(`${this.apiUrl}/create`, formData);
  }

  updateProduit(id: number, formData: FormData): Observable<Produit> {
    return this.http.put<Produit>(`${this.apiUrl}/update/${id}`, formData);
  }

  deleteProduit(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/${id}`);
  }

  getProduitByNom(nom: string): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/nom/${nom}`);
  }

  getProduitsByPrixRange(min: number, max: number): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/prix?min=${min}&max=${max}`);
  }

  getProduitsEnStock(): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/en-stock`);
  }
  getProduitById(id: number): Observable<Produit> {
    return this.http.get<Produit>(`${this.apiUrl}/${id}`);
  } 
}