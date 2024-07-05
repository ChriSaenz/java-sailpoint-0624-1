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

/** DepartmentController
 * Controller for handling department requests made by frontend.
 */
@RestController
@RequestMapping("/dept")
@CrossOrigin(origins = "*")
public class DepartmentController {
	@Autowired private DepartmentService service;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** getAllDepartments
	 * Calls DepartmentService to get all departments.
	 * @return ResponseEntity containing all departments in database table.
	 */
	@GetMapping
	public ResponseEntity<Iterable<Department>> getAllDepartments() {
		logger.info("Message", "getAllDepartments() called");
		return service.getAllDepartments();
	}
	
	/** getDepartmentById
	 * Calls DepartmentService to return a specific department by its id.
	 * @param id  - Id of department to get.
	 * @return ResponseEntity containing Department with corresponding id, or null if the operation failed.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable int id) {
		logger.info("Message", "getDepartmentById(" + id + ") called");
		return service.getDepartmentById(id);
	}
	
	/** addDepartment 
	 * Calls DepartmentService to add a new department.
	 * @param department - Object containing department to add.
	 * @return ResponseEntity containing the added Department, or null if the operation failed.
	 */
	@PostMapping()
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		logger.info("Message", "addDepartment(" + department + ") called");
		return service.addDepartment(department);
	}
	
	/** updateDepartment
	 * Calls DepartmentService to update an existing department with specified information.
	 * @param idToUpdate - Id of Department to update.
	 * @param deptName - New department name.
	 * @return ResponseEntity containing the updated Department, or null if the operation failed.
	 */
	@PutMapping("/{idToUpdate}")
	public ResponseEntity<Department> updateDepartment(@PathVariable int idToUpdate,
														@RequestParam String deptName) {
		logger.info("Message", "updateDepartment(" + idToUpdate + ", " + deptName + ") called");
		return service.updateDepartment(idToUpdate, deptName);
	}
	
	/** deleteDepartmentById
	 * Calls DepartmentService to delete a department from the database.
	 * @param id - Id of the Department to delete.
	 * @return ResponseEntity containing the deleted Department, or null if the operation failed.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Department> deleteDepartmentById(@PathVariable int id) {
		logger.info("Message", "deleteDepartmentById(" + id + ") called");
		return service.deleteDepartmentById(id);
	}
}
