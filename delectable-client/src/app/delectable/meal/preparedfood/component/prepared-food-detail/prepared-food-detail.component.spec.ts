import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreparedFoodDetailComponent } from './prepared-food-detail.component';

describe('PreparedFoodDetailComponent', () => {
  let component: PreparedFoodDetailComponent;
  let fixture: ComponentFixture<PreparedFoodDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreparedFoodDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreparedFoodDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
