import { TestBed } from '@angular/core/testing';

import { RetourProduitService } from './retour-produit.service';

describe('RetourProduitService', () => {
  let service: RetourProduitService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RetourProduitService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
