import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { HistoriqueRetourService } from '../../services/historique-retour.service';
import { HistoriqueRetour } from '../../Models/historique-retour.model.ts';

@Component({
  selector: 'app-historique-retour',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './historique-retour.component.html',
  styleUrls: ['./historique-retour.component.css']
})
export class HistoriqueRetourComponent implements OnInit {
  historiques: HistoriqueRetour[] = [];
  displayedColumns: string[] = ['id', 'action', 'date', 'employeId', 'retourId'];

  constructor(private historiqueService: HistoriqueRetourService) {}

  ngOnInit(): void {
    this.loadHistoriques();
  }

  loadHistoriques(): void {
    this.historiqueService.getAll().subscribe({
      next: (data) => this.historiques = data,
      error: (err) => console.error('Erreur lors du chargement des historiques :', err)
    });
  }
}
