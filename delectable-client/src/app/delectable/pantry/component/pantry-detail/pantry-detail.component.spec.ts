import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PantryDetailComponent } from './pantry-detail.component';

describe('PantryDetailComponent', () => {
  let component: PantryDetailComponent;
  let fixture: ComponentFixture<PantryDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PantryDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PantryDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
