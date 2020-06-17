import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreparedFoodListComponent } from './prepared-food-list.component';

describe('PreparedFoodListComponent', () => {
  let component: PreparedFoodListComponent;
  let fixture: ComponentFixture<PreparedFoodListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreparedFoodListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreparedFoodListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
