import { Department } from "./department";

export class Employee {
    emplId: number;
    emplFirstName: string;
    emplLastName: string;
    emplTitle: string;
    department: Department;

    constructor(emplId: number, emplFirstName: string,
            emplLastName: string, emplTitle: string,
            department: Department) {
        this.emplId = emplId;
        this.emplFirstName = emplFirstName;
        this.emplLastName = emplLastName;
        this.emplTitle = emplTitle;
        this.department = department;
    }
}