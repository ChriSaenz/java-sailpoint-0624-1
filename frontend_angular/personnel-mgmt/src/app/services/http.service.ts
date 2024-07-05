import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../models/employee';
import { Department } from '../models/department';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  url:string = "http://localhost:8080/";
  urlEmpl:string = "http://localhost:8080/empl";
  urlDept:string = "http://localhost:8080/dept";
  
  constructor(private http:HttpClient) {}

  //  Employee methods

  /** getAllEmployees
   * Basic GET request to employee backend.
   * @returns Observable with the HttpResponse from the backend.
   */
  getAllEmployees(): Observable<HttpResponse<any>> {
    return this.http.get(this.urlEmpl, {observe: "response"});
  }

  /** getEmployeeById
   * Basic GET request to employee backend for employee with specified id.
   * @param id - ID of Employee to GET.
   * @returns Observable with the HttpResponse from the backend.
   */
  getEmployeeById(id: number): Observable<HttpResponse<any>> {
    return this.http.get(this.urlEmpl + "/" + id, {observe: "response"});
  }

  /** createEmployee
   * Basic POST request to employee backend to add new Employee.
   * @param empl - Employee object with Employee to add.
   * @returns Observable with the HttpResponse from the backend.
   */
  createEmployee(empl: Employee): Observable<HttpResponse<any>> {
    return this.http.post(this.urlEmpl, empl, {observe: "response"});
  }

  /** updateEmployee
   * PUT request to employee backend for employee with specific id.
   * Uses HttpParams to pass updated information.
   * @param empl - Employee object containing information to be used as params.
   * @returns Observable with the HttpResponse from the backend.
   */
  updateEmployee(empl: Employee): Observable<HttpResponse<any>> {
    let parameters = new HttpParams().set('emplFirstName', empl.emplFirstName)
        .set('emplLastName', empl.emplLastName).set('emplTitle', empl.emplTitle)
          .set('department', empl.department.deptId);
    return this.http.put(this.urlEmpl + "/" + empl.emplId, [], {
      observe: "response", params: parameters
    });
  }

  /** deleteEmployee
   * Basic DELETE request to employee backend for employee with specific id.
   * @param id - ID of Employee to delete.
   * @returns Observable with the HttpResponse from the backend.
   */
  deleteEmployee(id: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.urlEmpl + "/" + id, {observe: "response"});
  }

  //  Department methods

  /** getAllDepartments
   * Basic GET request to department backend.
   * @returns Observable with the HttpResponse from the backend.
   */
  getAllDepartments(): Observable<HttpResponse<any>> {
    return this.http.get(this.urlDept, {observe: "response"});
  }

  /** getDepartmentById
   * Basic GET request to department backend for department with specified id.
   * @param id - ID of Department to GET.
   * @returns Observable with the HttpResponse from the backend.
   */
  getDepartmentById(id: number): Observable<HttpResponse<any>> {
    return this.http.get(this.urlDept + "/" + id, {observe: "response"});
  }

  /** createDepartment
   * Basic POST request to department backend to add new Department.
   * @param empl - Department object with Department to add.
   * @returns Observable with the HttpResponse from the backend.
   */
  createDepartment(dept: Department): Observable<HttpResponse<any>> {
    return this.http.post(this.urlDept, dept, {observe: "response"});
  }

  /** updateDepartment
   * PUT request to department backend for department with specific id.
   * Uses HttpParams to pass updated information.
   * @param empl - department object containing information to be used as params.
   * @returns Observable with the HttpResponse from the backend.
   */
  updateDepartment(dept: Department): Observable<HttpResponse<any>> {
    let parameters = new HttpParams().set('deptName', dept.deptName);
    return this.http.put(this.urlDept + "/" +  dept.deptId, [], {
      observe: "response", params: parameters
    });
  }

  /** deleteDepartment
   * Basic DELETE request to department backend for department with specific id.
   * @param id - ID of Department to delete.
   * @returns Observable with the HttpResponse from the backend.
   */
  deleteDepartment(id: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.urlDept + "/" + id, {observe: "response"});
  }
}
