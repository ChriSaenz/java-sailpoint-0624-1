package com.skillstorm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**	Employee
 * Model class for representing an employee entry.
 */
@Entity
@Table(name = "employee")
public class Employee {
	
	//	ID of Employee in database.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empl_id") private int emplId;
	
	//	First name of Employee.
	@Column(name = "empl_firstname") private String emplFirstName;
	
	//	Last name of Employee.
	@Column(name = "empl_lastname") private String emplLastName;
	
	//	Employee profession title.
	@Column(name = "empl_title") private String emplTitle;
	
	//	Department Employee is in.
	@ManyToOne
	@JoinColumn(name="dept_id", referencedColumnName="dept_id")
	private Department department;
	
	public Employee() {super();}
	
	public Employee(int emplId, String emplFirstName, String emplLastName, String emplTitle, Department department) {
		super();
		this.emplId = emplId;
		this.emplFirstName = emplFirstName;
		this.emplLastName = emplLastName;
		this.emplTitle = emplTitle;
		this.department = department;
	}

	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	public String getEmplFirstName() {
		return emplFirstName;
	}

	public void setEmplFirstName(String emplFirstName) {
		this.emplFirstName = emplFirstName;
	}

	public String getEmplLastName() {
		return emplLastName;
	}

	public void setEmplLastName(String emplLastName) {
		this.emplLastName = emplLastName;
	}

	public String getEmplTitle() {
		return emplTitle;
	}

	public void setEmplTitle(String emplTitle) {
		this.emplTitle = emplTitle;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "Employee:" + emplFirstName + " " + emplLastName + ", " + emplTitle + "(Department: " + department.getDeptName() + ")";
	}
}
