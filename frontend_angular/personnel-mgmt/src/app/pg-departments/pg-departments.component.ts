import { Component } from '@angular/core';
import { HttpService } from '../services/http.service';
import { Department } from '../models/department';

@Component({
  selector: 'app-pg-departments',
  standalone: true,
  imports: [],
  templateUrl: './pg-departments.component.html',
  styleUrl: '../display-page.css'
})
export class PgDepartmentsComponent {
  departments: Department[] = [];

  msg: string = "";

  constructor(private httpSrv: HttpService) {
    this.getAllDepartments();
    this.resetAddEditForm(false);
  }

  //  CRUD operations

  getAllDepartments() {
    this.httpSrv.getAllDepartments().subscribe(resp => {
      this.departments = [];
      for(let r of resp.body) {
        this.departments.push(new Department(r.deptId, r.deptName));
      }
      this.departments.sort((a, b) => {
        return a.deptId - b.deptId;
      });
    })
  }

  createDepartment() {
    let formElem = this.getFormElements();
    if(formElem) {
      let deptName = formElem.value;
      if(!deptName) {
        this.msg = "ERROR: All fields in Department form must have valid non-empty values."
        return;
      }

      this.httpSrv.createDepartment(new Department(-1, deptName)).subscribe(resp => {
        this.getAllDepartments();
        this.resetAddEditForm(false);
        this.msg = "Added Department " + resp.body.deptName + " (ID: " + resp.body.deptId + ").";
      });
    }
  }

  departmentToEdit: number = -1;

  updateDepartment() {
    let formElem = this.getFormElements();
    if(formElem) {
      let deptName = formElem.value;
      if(!deptName) {
        this.msg = "ERROR: All fields in Department form must have valid non-empty values."
        return;
      }

      this.httpSrv.updateDepartment(new Department(this.departmentToEdit, deptName))
        .subscribe(resp => {
          this.getAllDepartments();
          this.resetAddEditForm(false);
          this.msg = "Updated Department " + resp.body.deptName + " (ID: " + resp.body.deptId + ").";
        });
    }
  }

  departmentToDelete: number = -1;

  deleteDepartment(dept: Department) {
    if(this.departmentToDelete != dept.deptId) {
      this.departmentToDelete = dept.deptId;
      this.msg = "You are about to delete Department " + dept.deptName + " (ID: " + this.departmentToDelete + "). To continue, press the Delete button again.";
    }
    else {
      this.httpSrv.deleteDepartment(this.departmentToDelete).subscribe(resp => {
        this.getAllDepartments();
        this.msg = "Deleted Department " + dept.deptName + " (ID: " + this.departmentToDelete + ").";
        this.departmentToDelete = -1;
      });
    }
  }

  //  Misc operations

  getFormElements() {
    return document.getElementById("departmentName") as HTMLInputElement;
  }

  applyFilters() {
    // TOOD
  }

  resetAddEditForm(calledFromButton: boolean) {
    let formElem = this.getFormElements();
    if(formElem) {
      formElem.value = "";
    }
    this.departmentToEdit = -1;
    this.departmentToDelete = -1;
    this.msg = calledFromButton ? "Reset Department form." : "";
  }

  prepareDepartmentToEdit(id: number) {
    this.httpSrv.getDepartmentById(id).subscribe(resp => {
      this.departmentToEdit = id;
      let formElem = this.getFormElements();
      if(formElem) {
        formElem.value = resp.body.deptName;
      }

      this.msg = "Editing Department " + resp.body.deptName + " (ID: " + this.departmentToEdit + ")...";
    });
    this.departmentToDelete = -1;
  }

  sortBy(field: string) {
    switch(field) {
      case "id": {
        this.departments.sort((a,b) => {return a.deptId - b.deptId});
        break;
      }
      case "name": {
        this.departments.sort((a,b) => {
          return a.deptName > b.deptName ? 1 : (b.deptName < a.deptName ? -1 : 0);
        });
        break;
      }
      default: break;
    }
  }
}
