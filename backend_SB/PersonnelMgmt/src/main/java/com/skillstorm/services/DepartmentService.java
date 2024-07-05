package com.skillstorm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.models.Department;
import com.skillstorm.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired private DepartmentRepository repo;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ResponseEntity<Iterable<Department>> getAllDepartments() {
		logger.info("getAllDepartments() resolved successfully");
		return ResponseEntity.status(200).header("Message", "Returned all departments")
				.body(repo.findAll());
	}
	
	public ResponseEntity<Department> getDepartmentById(int id) {
		if(!repo.existsById(id)) {
			logger.error("getDepartmentById() resulted in error: No Department exists with id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("Error", "No Department exists with id " + id).body(null);
		}
		logger.info("getDepartmentById() resolved successfully");
		return ResponseEntity.status(200).header("Message", "Returned all departments")
				.body(repo.findById(id).get());
	}
	
	public ResponseEntity<Department> addDepartment(Department department) {
		if(repo.existsById(department.getDeptId())) {
			logger.error("addDepartment() resulted in error: A Department with id " + department.getDeptId() + " already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.header("Message", "A Department with id " + department.getDeptId() + " already exists")
					.body(repo.findById(department.getDeptId()).get());
		}
		try {
			logger.info("addDepartment() resolved successfully");
			return ResponseEntity.status(200).header("Message", "New Department added: " + department)
					.body(repo.save(department));

		} catch(Exception e) {
			logger.error("addDepartment() resulted in exception: " + e);
		}
		return ResponseEntity.status(500).header("Error", "An error occurred").body(null);
	}
	
	public ResponseEntity<Department> updateDepartment(int idToUpdate, String newName) {
		if(!repo.existsById(idToUpdate)) {
			logger.error("updateDepartment() resulted in error: No Department exists with id " + idToUpdate);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("Error", "No Department exists with id " + idToUpdate).body(null);
		}
		logger.info("updateDepartment() resolved successfully");
		return ResponseEntity.status(200).header("Message", "Updated department with id " + idToUpdate)
				.body(repo.save(new Department(idToUpdate, newName)));
	}
	
	public ResponseEntity<Department> deleteDepartmentById(int id) {
		if(!repo.existsById(id)) {
			logger.error("deleteDepartmentById() resulted in error: No Department exists with id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("Error", "No Department exists with id " + id).body(null);
		}
		Department d = repo.findById(id).get();
		repo.deleteById(id);
		logger.info("deleteDepartmentById() resolved successfully");
		return ResponseEntity.status(200).header("Message", "Deleted department with id " + id).body(d);
	}
}
