import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PantryCardInfoComponent } from './pantry-card-info.component';

describe('PantryCardInfoComponent', () => {
  let component: PantryCardInfoComponent;
  let fixture: ComponentFixture<PantryCardInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PantryCardInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PantryCardInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
