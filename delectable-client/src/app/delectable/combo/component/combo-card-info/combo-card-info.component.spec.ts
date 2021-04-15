import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ComboCardInfoComponent } from './combo-card-info.component';

describe('ComboCardInfoComponent', () => {
  let component: ComboCardInfoComponent;
  let fixture: ComponentFixture<ComboCardInfoComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ComboCardInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComboCardInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
