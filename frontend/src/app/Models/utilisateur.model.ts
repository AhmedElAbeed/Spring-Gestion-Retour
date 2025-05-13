export interface Utilisateur {
  id?: number; // Make id optional to handle cases where it may be undefined
  email: string;
  nom: string;
  role: string;
}
