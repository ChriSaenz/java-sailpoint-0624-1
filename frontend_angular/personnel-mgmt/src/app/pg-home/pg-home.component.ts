import { Component } from '@angular/core';
import { HttpService } from '../services/http.service';

@Component({
  selector: 'app-pg-home',
  standalone: true,
  imports: [],
  templateUrl: './pg-home.component.html',
  styleUrl: './pg-home.component.css'
})
export class PgHomeComponent {
  emplCount: number = 0;
  deptCount: number = 0;

  constructor(private httpSrv: HttpService) {
    this.loadEmployeeCount();
    this.loadDepartmentCount();
  }

  loadEmployeeCount() {
    this.httpSrv.getAllEmployees().subscribe(resp => {
      this.emplCount = 0;
      for(let r in resp.body) {this.emplCount++;}
    });
  }

  loadDepartmentCount() {
    this.httpSrv.getAllDepartments().subscribe(resp => {
      this.deptCount = 0;
      for(let r in resp.body) {this.deptCount++;}
    });
  }
}
