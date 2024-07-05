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

  getAllEmployees(): Observable<HttpResponse<any>> {
    return this.http.get(this.urlEmpl, {observe: "response"});
  }

  getEmployeeById(id: number): Observable<HttpResponse<any>> {
    return this.http.get(this.urlEmpl + "/" + id, {observe: "response"});
  }

  createEmployee(empl: Employee): Observable<HttpResponse<any>> {
    return this.http.post(this.urlEmpl, empl, {observe: "response"});
  }

  updateEmployee(empl: Employee): Observable<HttpResponse<any>> {
    let parameters = new HttpParams().set('emplFirstName', empl.emplFirstName)
        .set('emplLastName', empl.emplLastName).set('emplTitle', empl.emplTitle)
          .set('department', empl.department.deptId);
    return this.http.put(this.urlEmpl + "/" + empl.emplId, [], {
      observe: "response", params: parameters
    });
  }

  deleteEmployee(id: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.urlEmpl + "/" + id, {observe: "response"});
  }

  //  Department methods

  getAllDepartments(): Observable<HttpResponse<any>> {
    return this.http.get(this.urlDept, {observe: "response"});
  }

  getDepartmentById(id: number): Observable<HttpResponse<any>> {
    return this.http.get(this.urlDept + "/" + id, {observe: "response"});
  }

  createDepartment(dept: Department): Observable<HttpResponse<any>> {
    return this.http.post(this.urlDept, dept, {observe: "response"});
  }

  updateDepartment(dept: Department): Observable<HttpResponse<any>> {
    let parameters = new HttpParams().set('deptName', dept.deptName);
    return this.http.put(this.urlDept + "/" +  dept.deptId, [], {
      observe: "response", params: parameters
    });
  }

  deleteDepartment(id: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.urlDept + "/" + id, {observe: "response"});
  }
}
