import { TestBed } from '@angular/core/testing';

import { HistoriqueRetourService } from './historique-retour.service';

describe('HistoriqueRetourService', () => {
  let service: HistoriqueRetourService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HistoriqueRetourService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
