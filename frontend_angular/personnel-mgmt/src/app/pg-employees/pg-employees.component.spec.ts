import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PgEmployeesComponent } from './pg-employees.component';

describe('PgEmployeesComponent', () => {
  let component: PgEmployeesComponent;
  let fixture: ComponentFixture<PgEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PgEmployeesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PgEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
