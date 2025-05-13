import { Routes } from '@angular/router';
import { HistoriqueRetourComponent } from './components/historique-retour/historique-retour.component';
import { NonConformiteComponent } from './components/non-conformite/non-conformite.component';
import { RetourProduitComponent } from './components/retour-produit/retour-produit.component';
import { UtilisateurComponent } from './components/utilisateur/utilisateur.component';

export const routes: Routes = [
  { path: '', redirectTo: '/non-conformite', pathMatch: 'full' }, // Default route
  { path: 'historique-retour', component: HistoriqueRetourComponent },
  { path: 'non-conformite', component: NonConformiteComponent },
  { path: 'retour-produit', component: RetourProduitComponent },
  { path: 'utilisateur', component: UtilisateurComponent }
];
