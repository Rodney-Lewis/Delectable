import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreparedFoodFormComponent } from './prepared-food-form.component';

describe('PreparedFoodFormComponent', () => {
  let component: PreparedFoodFormComponent;
  let fixture: ComponentFixture<PreparedFoodFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreparedFoodFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreparedFoodFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
