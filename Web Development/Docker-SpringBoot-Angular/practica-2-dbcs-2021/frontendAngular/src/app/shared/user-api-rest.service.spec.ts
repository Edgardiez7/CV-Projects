import { TestBed } from '@angular/core/testing';

import { UserApiRestService } from './user-api-rest.service';

describe('UserApiRestService', () => {
  let service: UserApiRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserApiRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
