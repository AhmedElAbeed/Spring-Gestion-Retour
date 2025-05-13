import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NonConformite } from '../Models/non-conformite.model';

@Injectable({
  providedIn: 'root'
})
export class NonConformiteService {
  private apiUrl = 'http://localhost:8082/non-conformite';

  constructor(private http: HttpClient) {}

  getAllNonConformites(): Observable<NonConformite[]> {
    return this.http.get<NonConformite[]>(this.apiUrl);
  }

  getNonConformiteById(id: number): Observable<NonConformite> {
    return this.http.get<NonConformite>(`${this.apiUrl}/${id}`);
  }

  createNonConformite(nc: NonConformite): Observable<NonConformite> {
    return this.http.post<NonConformite>(this.apiUrl, nc);
  }

  updateNonConformite(id: number, nc: NonConformite): Observable<NonConformite> {
    return this.http.put<NonConformite>(`${this.apiUrl}/${id}`, nc);
  }

  deleteNonConformite(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
