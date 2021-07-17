import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ComboDetailComponent } from './combo-detail.component';

describe('ComboDetailComponent', () => {
  let component: ComboDetailComponent;
  let fixture: ComponentFixture<ComboDetailComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ComboDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComboDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
