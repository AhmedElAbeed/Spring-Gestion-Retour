export interface RetourProduit {
  id?: number;  // Make the ID optional for new retour produits
  produitId: number;
  client: string;
  raison: string;
  etatTraitement: string;
  date: string;
  quantityReturned: number | null;  // Add quantityReturned here
}
