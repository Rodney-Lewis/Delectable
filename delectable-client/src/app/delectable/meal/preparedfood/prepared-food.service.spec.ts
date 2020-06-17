import { TestBed } from '@angular/core/testing';

import { PreparedFoodService } from './prepared-food.service';

describe('PreparedFoodService', () => {
  let service: PreparedFoodService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PreparedFoodService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
