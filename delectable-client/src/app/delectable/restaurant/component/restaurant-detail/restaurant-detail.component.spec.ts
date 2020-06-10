import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RestuarantDetailComponent } from './restaurant-detail.component';

describe('RestuarantDetailComponent', () => {
  let component: RestuarantDetailComponent;
  let fixture: ComponentFixture<RestuarantDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RestuarantDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestuarantDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
