import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PgDepartmentsComponent } from './pg-departments.component';

describe('PgDepartmentsComponent', () => {
  let component: PgDepartmentsComponent;
  let fixture: ComponentFixture<PgDepartmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PgDepartmentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PgDepartmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
