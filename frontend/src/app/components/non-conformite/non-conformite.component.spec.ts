import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NonConformiteComponent } from './non-conformite.component';

describe('NonConformiteComponent', () => {
  let component: NonConformiteComponent;
  let fixture: ComponentFixture<NonConformiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NonConformiteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NonConformiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
