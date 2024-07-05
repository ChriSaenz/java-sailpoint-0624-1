package com.skillstorm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.models.Department;
import com.skillstorm.services.DepartmentService;

@RestController
@RequestMapping("/dept")
@CrossOrigin(origins = "*")
public class DepartmentController {
	@Autowired private DepartmentService service;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping
	public ResponseEntity<Iterable<Department>> getAllDepartments() {
		logger.info("Message", "getAllDepartments() called");
		return service.getAllDepartments();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable int id) {
		logger.info("Message", "getDepartmentById(" + id + ") called");
		return service.getDepartmentById(id);
	}
	
	@PostMapping()
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		logger.info("Message", "addDepartment(" + department + ") called");
		return service.addDepartment(department);
	}
	
	@PutMapping("/{idToUpdate}")
	public ResponseEntity<Department> updateDepartment(@PathVariable int idToUpdate,
														@RequestParam String deptName) {
		logger.info("Message", "updateDepartment(" + idToUpdate + ", " + deptName + ") called");
		return service.updateDepartment(idToUpdate, deptName);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Department> deleteDepartmentById(@PathVariable int id) {
		logger.info("Message", "deleteDepartmentById(" + id + ") called");
		return service.deleteDepartmentById(id);
	}
}
