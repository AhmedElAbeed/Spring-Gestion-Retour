import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HistoriqueRetour } from '../Models/historique-retour.model.ts';

@Injectable({
  providedIn: 'root'
})
export class HistoriqueRetourService {
  private apiUrl = 'http://localhost:8082/historique-retour';

  constructor(private http: HttpClient) {}

  getAll(): Observable<HistoriqueRetour[]> {
    return this.http.get<HistoriqueRetour[]>(this.apiUrl);
  }

  getById(id: number): Observable<HistoriqueRetour> {
    return this.http.get<HistoriqueRetour>(`${this.apiUrl}/${id}`);
  }

  create(historique: HistoriqueRetour): Observable<HistoriqueRetour> {
    return this.http.post<HistoriqueRetour>(this.apiUrl, historique);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
