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
  employeesInDepts: Map<number, number> = new Map<number, number>();

  msg: string = "";

  constructor(private httpSrv: HttpService) {
    this.getAllDepartments();
    this.resetAddEditForm(false);
  }

  //  CRUD operations

  /** getAllDepartments
   * Gets all departments via the HttpService, then puts them in an array to display.
   */
  getAllDepartments() {
    this.httpSrv.getAllDepartments().subscribe(resp => {
      this.departments = [];
      for(let r of resp.body) {
        this.departments.push(new Department(r.deptId, r.deptName));
      }
      this.departments.sort((a, b) => {
        return a.deptId - b.deptId;
      });
      this.countEmployeesInDepts();
    })
  }

  /** createDepartment
   * Creates a new department with information from the form, then posts it via the HttpService.
   * @returns Nothing, is used to prevent bad data.
   */
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

  //  If this is not -1, we know we're editing something and not adding.
  departmentToEdit: number = -1;

  /** updateDepartment
   * Gathers information from the form, then updates the department selected to edit with that information.
   * @returns Nothing, is used to prevent bad data.
   */
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

  //  If this is not -1, we know we're ready to delete something.
  departmentToDelete: number = -1;

  /** deleteDepartment
   * On first use, warns the user about deletion.
   * On second use passing the same department, deletes the department from the database via the HttpService.
   * @param emp - Department to delete, used for checking ID and confirming deletion.
   */
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

  /** countEmployeesInDepts
   * Fills out the Map for counting how many Employees there are in each Department.
   * This Map is used for display in a table column.
   */
  countEmployeesInDepts() {
    this.httpSrv.getAllEmployees().subscribe(resp => {
      for(let d of this.departments) {
        this.employeesInDepts.set(d.deptId, 0);
      }
      for(let e of resp.body) {
        let val = this.employeesInDepts.get(e.department.deptId);
        if(val !== undefined) {
          this.employeesInDepts.set(e.department.deptId, val + 1);
        }
      }
    });
  }

  /** getFormElements
   * Used to make referencing form elements easier.
   * @returns Array of references to HTMLInput/SelectElements, to destructure and verify before use.
   */
  getFormElements() {
    return document.getElementById("departmentName") as HTMLInputElement;
  }

  applyFilters() {/* TOOD */}

  /** resetAddEditForm
   * Resets information and state of the Add/Update Department form.
   * @param calledFromButton - Used for printing message of reset or not.
   */
  resetAddEditForm(calledFromButton: boolean) {
    let formElem = this.getFormElements();
    if(formElem) {
      formElem.value = "";
    }
    this.departmentToEdit = -1;
    this.departmentToDelete = -1;
    this.msg = calledFromButton ? "Reset Department form." : "";
  }

  /** prepareDepartmentToEdit
   * Called to load department information into the Add/Edit Department form so the user can edit the department easily.
   * @param id - Id of Department to edit.
   */
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

  /** sortBy
   * A series of sorts to be used for the columns of the display table.
   */
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
