import { TestBed } from '@angular/core/testing';

import { CreationSession1Service } from './CreationSession.service';

describe('CreationSession1Service', () => {
  let service: CreationSession1Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreationSession1Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
