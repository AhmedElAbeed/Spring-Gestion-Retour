// services/retour-produit.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RetourProduit } from '../Models/retour-produit.model';

@Injectable({
  providedIn: 'root'
})
export class RetourProduitService {
  private apiUrl = 'http://localhost:8082/retour-produit';  // Base URL for your backend API

  constructor(private http: HttpClient) {}

  // Fetch all retour produits
  getAllRetours(): Observable<RetourProduit[]> {
    return this.http.get<RetourProduit[]>(this.apiUrl);
  }

  // Fetch a single retour produit by ID
  getRetourById(id: number): Observable<RetourProduit> {
    return this.http.get<RetourProduit>(`${this.apiUrl}/${id}`);
  }

  // Create a new retour produit
  createRetour(retourProduit: RetourProduit): Observable<RetourProduit> {
    return this.http.post<RetourProduit>(this.apiUrl, retourProduit);
  }

  // Update an existing retour produit
  updateRetour(id: number, retourProduit: RetourProduit): Observable<RetourProduit> {
    return this.http.put<RetourProduit>(`${this.apiUrl}/${id}`, retourProduit);
  }

  // Delete a retour produit by ID
  deleteRetour(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
