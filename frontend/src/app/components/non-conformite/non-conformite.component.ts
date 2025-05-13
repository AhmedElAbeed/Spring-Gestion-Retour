import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NonConformiteService } from '../../services/non-conformite.service';
import { NonConformite } from '../../Models/non-conformite.model';

@Component({
  selector: 'app-non-conformite',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './non-conformite.component.html',
  styleUrls: ['./non-conformite.component.css']
})
export class NonConformiteComponent implements OnInit {
  nonConformites: NonConformite[] = [];
  newNonConformite: NonConformite = new NonConformite();
  isEditing = false;

  constructor(private nonConformiteService: NonConformiteService) {}

  ngOnInit(): void {
    this.loadNonConformites();
  }

  loadNonConformites(): void {
    this.nonConformiteService.getAllNonConformites().subscribe(data => {
      this.nonConformites = data;
    });
  }

  onSubmit(): void {
    if (this.isEditing) {
      this.nonConformiteService.updateNonConformite(this.newNonConformite.id!, this.newNonConformite).subscribe(() => {
        this.loadNonConformites();
        this.resetForm();
      });
    } else {
      this.nonConformiteService.createNonConformite(this.newNonConformite).subscribe(() => {
        this.loadNonConformites();
        this.resetForm();
      });
    }
  }

  edit(nonConformite: NonConformite): void {
    this.newNonConformite = { ...nonConformite };
    this.isEditing = true;
  }

  delete(id: number | undefined): void {
    if (!id) return;
    this.nonConformiteService.deleteNonConformite(id).subscribe(() => {
      this.loadNonConformites();
    });
  }

  resetForm(): void {
    this.newNonConformite = new NonConformite();
    this.isEditing = false;
  }
}
