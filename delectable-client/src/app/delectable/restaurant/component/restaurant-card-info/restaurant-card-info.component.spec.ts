import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { RestaurantCardInfoComponent } from './restaurant-card-info.component';

describe('RestaurantCardInfoComponent', () => {
  let component: RestaurantCardInfoComponent;
  let fixture: ComponentFixture<RestaurantCardInfoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ RestaurantCardInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantCardInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
