import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ScheduleWeekAtAGlanceComponent } from './schedule-week-at-a-glance.component';

describe('ScheduleWeekAtAGlanceComponent', () => {
  let component: ScheduleWeekAtAGlanceComponent;
  let fixture: ComponentFixture<ScheduleWeekAtAGlanceComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ScheduleWeekAtAGlanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScheduleWeekAtAGlanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
