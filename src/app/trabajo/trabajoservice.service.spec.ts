import { TestBed } from '@angular/core/testing';

import { TrabajoserviceService } from './trabajoservice.service';

describe('TrabajoserviceService', () => {
  let service: TrabajoserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrabajoserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
