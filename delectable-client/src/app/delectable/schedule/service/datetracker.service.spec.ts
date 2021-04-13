import { TestBed } from '@angular/core/testing';

import { DatetrackerService } from './datetracker.service';

describe('DatetrackerService', () => {
  let service: DatetrackerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DatetrackerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
