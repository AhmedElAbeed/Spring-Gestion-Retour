import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetourProduitComponent } from './retour-produit.component';

describe('RetourProduitComponent', () => {
  let component: RetourProduitComponent;
  let fixture: ComponentFixture<RetourProduitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RetourProduitComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RetourProduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
