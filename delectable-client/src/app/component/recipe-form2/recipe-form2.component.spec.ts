import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeForm2Component } from './recipe-form2.component';

describe('RecipeForm2Component', () => {
  let component: RecipeForm2Component;
  let fixture: ComponentFixture<RecipeForm2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecipeForm2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeForm2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
