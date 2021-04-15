import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { CarddeckComponent } from './carddeck.component';

describe('CarddeckComponent', () => {
  let component: CarddeckComponent;
  let fixture: ComponentFixture<CarddeckComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ CarddeckComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarddeckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
