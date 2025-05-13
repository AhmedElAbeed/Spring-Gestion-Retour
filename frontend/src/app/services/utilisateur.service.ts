import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Utilisateur } from '../Models/utilisateur.model';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private apiUrl = 'http://localhost:8082/utilisateur';

  constructor(private http: HttpClient) {}

  // Get all utilisateurs
  getAllUtilisateurs(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.apiUrl);
  }

  // Get utilisateur by ID
  getUtilisateurById(id: number): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(`${this.apiUrl}/${id}`);
  }

  createUtilisateur(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(this.apiUrl, utilisateur, {
      headers: { 'Content-Type': 'application/json' } // Ensure JSON format
    });
  }
  

  // Update existing utilisateur
  updateUtilisateur(id: number, utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(`${this.apiUrl}/${id}`, utilisateur);
  }

  // Delete utilisateur by ID
  deleteUtilisateur(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
