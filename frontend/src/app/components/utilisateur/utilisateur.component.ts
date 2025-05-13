import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';  // Import FormsModule for ngModel
import { CommonModule } from '@angular/common';  // For *ngFor, *ngIf
import { UtilisateurService } from '../../services/utilisateur.service';  // Import the service
import { Utilisateur } from '../../Models/utilisateur.model';  // Import the model

@Component({
  selector: 'app-utilisateur',
  standalone: true,  // Marking the component as standalone
  imports: [CommonModule, FormsModule],  // Import necessary modules for directives and form binding
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.css']
})
export class UtilisateurComponent implements OnInit {
  utilisateurs: Utilisateur[] = [];
  newUtilisateur: Utilisateur = { email: '', nom: '', role: '' };

  constructor(private utilisateurService: UtilisateurService) {}

  ngOnInit(): void {
    this.getAllUtilisateurs();
  }

  getAllUtilisateurs(): void {
    this.utilisateurService.getAllUtilisateurs().subscribe((data) => {
      this.utilisateurs = data;
    });
  }
  addUtilisateur(): void {
    console.log("Adding user:", this.newUtilisateur); // Debugging log
    if (this.newUtilisateur.email && this.newUtilisateur.nom && this.newUtilisateur.role) {
      this.utilisateurService.createUtilisateur(this.newUtilisateur).subscribe(
        (response) => {
          console.log("User added successfully:", response);
          this.getAllUtilisateurs();
          this.newUtilisateur = { email: '', nom: '', role: '' }; // Reset form
        },
        (error) => {
          console.error("Error creating user:", error.error);
        }
      );
    } else {
      alert("All fields are required!");
    }
  }
  
  

  deleteUtilisateur(id?: number): void {
    if (id === undefined) {
      console.error('Error: ID is undefined, cannot delete utilisateur.');
      return;
    }
  
    if (confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')) {
      this.utilisateurService.deleteUtilisateur(id).subscribe(
        () => {
          this.getAllUtilisateurs(); // Refresh list after deletion
        },
        (error) => {
          console.error('Erreur lors de la suppression de l’utilisateur:', error.error);
        }
      );
    }
  }
  



  editUtilisateur(utilisateur: Utilisateur): void {
    this.newUtilisateur = { ...utilisateur };  // Pre-fill the form with the selected user's data
  }
}
