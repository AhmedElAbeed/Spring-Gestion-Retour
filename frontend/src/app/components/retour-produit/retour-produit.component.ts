import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RetourProduitService } from '../../services/retour-produit.service';
import { RetourProduit } from '../../Models/retour-produit.model';

@Component({
  selector: 'app-retour-produit',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './retour-produit.component.html',
  styleUrls: ['./retour-produit.component.css']
})
export class RetourProduitComponent implements OnInit {
  retourProduits: RetourProduit[] = []; // Holds the list of retour produits
  newRetourProduit: RetourProduit = { produitId: 0, client: '', raison: '', etatTraitement: 'PENDING', date: '', quantityReturned: null };  // Initialized with default values
  formError: string | null = null; // Holds error message for form validation

  constructor(private retourProduitService: RetourProduitService) {}

  ngOnInit(): void {
    this.getAllRetours();  // Fetch the list of retour produits on component load
  }

  // Fetch all retour produits from the server
  getAllRetours(): void {
    this.retourProduitService.getAllRetours().subscribe((data) => {
      this.retourProduits = data;
    });
  }

  // Add a new retour produit
  addRetourProduit(): void {
    console.log("🟢 Sending RetourProduit:", this.newRetourProduit);

    // Check if all required fields are filled
    if (!this.newRetourProduit.produitId || !this.newRetourProduit.client || !this.newRetourProduit.raison || !this.newRetourProduit.date) {
      this.formError = 'Please fill in all the required fields.'; // Show error if fields are missing
      console.error("⚠️ Missing required fields!", this.newRetourProduit);
      return; // Prevent sending an invalid request
    }

    // If the state is "ACCEPTED", ensure quantityReturned is provided
    if (this.newRetourProduit.etatTraitement === 'ACCEPTED' && this.newRetourProduit.quantityReturned === null) {
      this.formError = 'Quantity Returned is required when the status is Accepted.';
      console.error("⚠️ Missing quantityReturned for ACCEPTED status.");
      return;
    }

    this.formError = null; // Clear any previous error message

    // Make sure etatTraitement is always "PENDING" when creating a new retour produit
    this.newRetourProduit.etatTraitement = 'PENDING';

    // Call service to create a new retour produit
    this.retourProduitService.createRetour(this.newRetourProduit).subscribe(() => {
      console.log("✅ Retour produit added successfully");
      this.getAllRetours();  // Refresh the list after adding
      this.newRetourProduit = { produitId: 0, client: '', raison: '', etatTraitement: 'PENDING', date: '', quantityReturned: null }; // Reset form
    }, error => {
      console.error("❌ Error adding retour produit:", error);
    });
  }

  updateRetourProduit(id: number, newEtatTraitement: string): void {
    // Find the retour produit using the correct 'id'
    const retourProduitToUpdate = this.retourProduits.find(rp => rp.id === id);
    
    if (!retourProduitToUpdate || retourProduitToUpdate.id === undefined) {
      console.error("RetourProduit not found or invalid id.");
      return;
    }
  
    // Create a copy of the retour produit with updated status
    const updatedRetourProduit = { ...retourProduitToUpdate, etatTraitement: newEtatTraitement };
  
    // If the new status is ACCEPTED, ensure quantityReturned is provided
    if (newEtatTraitement === 'ACCEPTED' && updatedRetourProduit.quantityReturned === null) {
      console.error("⚠️ Missing quantityReturned for ACCEPTED status.");
      this.formError = 'Quantity Returned is required when the status is Accepted.';
      return;
    }
  
    // Call the service and pass the correct 'id' to the backend (not 'produitId')
    this.retourProduitService.updateRetour(retourProduitToUpdate.id, updatedRetourProduit).subscribe(() => {
      console.log(`✅ Retour produit updated to ${newEtatTraitement}`);
  
      this.getAllRetours();  // Refresh the list after update
    }, error => {
      console.error("❌ Error updating retour produit:", error);
    });
  }
  

  // Delete a retour produit
  deleteRetourProduit(id: number): void {
    if (id > 0) { // Ensure a valid ID is provided
      this.retourProduitService.deleteRetour(id).subscribe(() => {
        console.log(`✅ Deleted retour with ID: ${id}`);
        this.getAllRetours();  // Refresh the list after deletion
      }, error => {
        console.error("❌ Error deleting retour produit:", error);
      });
    } else {
      console.error("⚠️ Invalid ID for deletion:", id);
    }
  }

  validateQuantity(retourProduit: RetourProduit): void {
    // This function can be used to validate the quantity
    if (retourProduit.quantityReturned && retourProduit.quantityReturned < 1) {
      this.formError = "Quantity Returned must be a positive number.";
    } else {
      this.formError = null;
    }
  }
}
