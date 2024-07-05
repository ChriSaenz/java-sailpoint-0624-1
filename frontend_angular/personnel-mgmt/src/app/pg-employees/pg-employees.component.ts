import { Component } from '@angular/core';
import { HttpService } from '../services/http.service';
import { Employee } from '../models/employee';
import { Department } from '../models/department';

@Component({
  selector: 'app-pg-employees',
  standalone: true,
  imports: [],
  templateUrl: './pg-employees.component.html',
  styleUrl: '../display-page.css'
})
export class PgEmployeesComponent {
  employees: Employee[] = [];
  departments: Department[] = [];
  filters: Object = {};

  msg: string = "";

  constructor(private httpSrv: HttpService) {
    this.getAllEmployees();
    this.resetAddEditForm(false);
  }

  //  CRUD operations

  getAllEmployees() {
    this.httpSrv.getAllEmployees().subscribe(resp => {
      this.employees = [];
      for(let r of resp.body) {
        this.employees.push(new Employee(r.emplId, r.emplFirstName,
          r.emplLastName, r.emplTitle, r.department));
      }
    });
  }

  createEmployee() {
    //  Get data from form, check for validity
    let [elem1, elem2, elem3, elem4] = this.getFormElements();
    if(elem1 && elem2 && elem3 && elem4) {
      let emplFirstName = elem1.value,
      emplLastName = elem2.value,
      emplTitle = elem3.value,
      emplDeptId = elem4.value;
      if(!emplFirstName || !emplLastName || !emplTitle || !emplDeptId) {
        this.msg = "ERROR: All fields in Employee form must have valid non-empty values."
        return;
      }

      //  Get Department from emplDeptName
      let emplDept:Department = new Department(-1,"");
      for(let d of this.departments) {
        if(emplDeptId == d.deptId.toString()) {
          emplDept = d;
        }
      }

      // console.log(emplFirstName, emplLastName, emplTitle, emplDept.deptName);

      //  Call create
      this.httpSrv.createEmployee(new Employee(-1,  //  ID doesn't matter: autoincrement.
        emplFirstName, emplLastName, emplTitle, emplDept)).subscribe(resp => {
          this.getAllEmployees();
          this.resetAddEditForm(false);
          this.msg = "Added Employee " + resp.body.emplFirstName + " " + resp.body.emplLastName + " (ID: " + resp.body.emplId + ").";
      });
    }
  }
  
  //  If this is not -1, we know we're editing something and not adding.
  employeeToEdit:number = -1;

  updateEmployee() {
    let [elem1, elem2, elem3, elem4] = this.getFormElements();
    if(elem1 && elem2 && elem3 && elem4) {
      let emplFirstName = elem1.value,
      emplLastName = elem2.value,
      emplTitle = elem3.value,
      emplDeptId = elem4.value;
      if(!emplFirstName || !emplLastName || !emplTitle || !emplDeptId) {
        this.msg = "ERROR: All fields in Employee form must have valid non-empty values."
        return;
      }

      //  Get Department from emplDeptName
      let emplDept:Department = new Department(-1,"");
      for(let d of this.departments) {
        if(emplDeptId == d.deptId.toString()) {
          emplDept = d;
        }
      }

      // console.log(emplFirstName, emplLastName, emplTitle, emplDept.deptName);

      //  Call update
      this.httpSrv.updateEmployee(new Employee(this.employeeToEdit,
        emplFirstName, emplLastName, emplTitle, emplDept)).subscribe(resp => {
          this.getAllEmployees();
          this.resetAddEditForm(false);
          this.msg = "Updated Employee " + resp.body.emplFirstName + " " + resp.body.emplLastName + " (ID: " + resp.body.emplId + ").";
      });
    }
  }

  //  If this is not -1, we know we're ready to delete something.
  employeeToDelete: number = -1;

  deleteEmployee(emp: Employee) {
    if(this.employeeToDelete != emp.emplId) {
      this.employeeToDelete = emp.emplId;
      this.msg = "You are about to delete Employee " + emp.emplFirstName + " " + emp.emplLastName + " (ID: " + this.employeeToDelete + "). To continue, press the Delete button again.";
    }
    else {
      this.httpSrv.deleteEmployee(this.employeeToDelete).subscribe(resp => {
        this.getAllEmployees();
        this.msg = "Deleted Employee " + emp.emplFirstName + " " + emp.emplLastName + " (ID: " + this.employeeToDelete + ").";
        this.employeeToDelete = -1;
      });
    }
    this.getAllEmployees();
  }

  //  Misc operations

  getFormElements() {
    return [document.getElementById("employeeFirstName") as HTMLInputElement,
      document.getElementById("employeeLastName") as HTMLInputElement,
      document.getElementById("employeeTitle") as HTMLInputElement,
      document.getElementById("employeeDepartment") as HTMLSelectElement];
  }

  applyFilters() {/* TODO */}

  resetAddEditForm(calledFromButton: boolean) {
    let [elem1, elem2, elem3, elem4] = this.getFormElements();
    if(elem1 && elem2 && elem3 && elem4) {
      elem1.value = "";
      elem2.value = "";
      elem3.value = "";
    }
    this.updateDepartmentDropdown();
    this.employeeToEdit = -1;
    this.employeeToDelete = -1;
    this.msg = calledFromButton ? "Reset Employee form." : "";
  }

  updateDepartmentDropdown() {
    this.httpSrv.getAllDepartments().subscribe(resp => {
      this.departments = [];
      for(let r of resp.body) {
        this.departments.push(r);
      }
      this.departments.sort((a,b) => {
        return a.deptName > b.deptName ? 1 : (b.deptName > a.deptName ? -1 : 0)
      });
    });
  }
  
  prepareEmployeeToEdit(id: number) {
    this.httpSrv.getEmployeeById(id).subscribe(resp => {
      this.employeeToEdit = id;
      let [firstname, lastname, title, dept] = this.getFormElements();
      if(firstname && lastname && title && dept) {
        firstname.value = resp.body.emplFirstName;
        lastname.value = resp.body.emplLastName;
        title.value = resp.body.emplTitle;
        dept.value = resp.body.department.deptId;
      }

      this.msg = "Editing Employee " + resp.body.emplFirstName + " " + resp.body.emplLastName + " (ID: " + this.employeeToEdit + ")...";
    });
    this.employeeToDelete = -1;
  }

  sortBy(field: string) {
    switch(field) {
      case "id": {
        this.employees.sort((a,b) => {return a.emplId - b.emplId});
        break;
      }
      case "name_first": {
        this.employees.sort((a,b) => {
          return a.emplFirstName > b.emplFirstName ? 1 : (
            b.emplFirstName < a.emplFirstName ? -1 : 0);
        });
        break;
      }
      case "name_last": {
        this.employees.sort((a,b) => {
          return a.emplLastName > b.emplLastName ? 1 : (
            b.emplLastName < a.emplLastName ? -1 : 0);
        });
        break;
      }
      case "title": {
        this.employees.sort((a,b) => {
          return a.emplTitle > b.emplTitle ? 1 : (b.emplTitle < a.emplTitle ? -1 : 0);
        });
        break;
      }
      case "department": {
        this.employees.sort((a,b) => {
          return a.department.deptName > b.department.deptName ? 1 : (
            b.department.deptName < a.department.deptName ? -1 : 0);
        });
        break;
      }
      default: break;
    }
  }
}
