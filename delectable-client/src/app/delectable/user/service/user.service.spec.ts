import { TestBed } from '@angular/core/testing';

import { UserAccountService } from './user.service';

describe('UserService', () => {
  let service: UserAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserAccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
