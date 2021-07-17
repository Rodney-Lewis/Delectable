import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { RecipeCardInfoComponent } from './recipe-card-info.component';

describe('RecipeCardInfoComponent', () => {
  let component: RecipeCardInfoComponent;
  let fixture: ComponentFixture<RecipeCardInfoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ RecipeCardInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeCardInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
