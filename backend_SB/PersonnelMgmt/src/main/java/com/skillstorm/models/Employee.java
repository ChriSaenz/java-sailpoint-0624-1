package com.skillstorm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empl_id")
	private int emplId;
	@Column(name = "empl_firstname") private String emplFirstName;
	@Column(name = "empl_lastname") private String emplLastName;
	@Column(name = "empl_title") private String emplTitle;
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
}
