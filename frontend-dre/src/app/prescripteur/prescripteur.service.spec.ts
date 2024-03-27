import { TestBed } from '@angular/core/testing';

import { PrescripteurService } from './prescripteur.service';

describe('PrescripteurService', () => {
  let service: PrescripteurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrescripteurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
