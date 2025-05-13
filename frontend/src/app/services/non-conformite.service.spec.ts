import { TestBed } from '@angular/core/testing';

import { NonConformiteService } from './non-conformite.service';

describe('NonConformiteService', () => {
  let service: NonConformiteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NonConformiteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
