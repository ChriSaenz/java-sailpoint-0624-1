import { Routes } from '@angular/router';
import { PgHomeComponent } from './pg-home/pg-home.component';
import { PgEmployeesComponent } from './pg-employees/pg-employees.component';
import { PgDepartmentsComponent } from './pg-departments/pg-departments.component';

export const routes: Routes = [
    {path: "", component: PgHomeComponent},
    {path: "employees", component: PgEmployeesComponent},
    {path: "departments", component: PgDepartmentsComponent}
];
