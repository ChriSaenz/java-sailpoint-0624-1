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
import com.skillstorm.models.Employee;
import com.skillstorm.services.EmployeeService;

@RestController
@RequestMapping("/empl")
@CrossOrigin(origins = "*")
public class EmployeeController {
	@Autowired private EmployeeService service;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping
	public ResponseEntity<Iterable<Employee>> getAllEmployees() {
		logger.info("Message", "getAllEmployees() called");
		return service.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		logger.info("Message", "getEmployeeById(" + id + ") called");
		return service.getEmployeeById(id);
	}
	
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		logger.info("Message", "addEmployee(" + employee + ") called");
		return service.addEmployee(employee);
	}
	
	@PutMapping("/{idToUpdate}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int idToUpdate,
													@RequestParam String emplFirstName,
													@RequestParam String emplLastName,
													@RequestParam String emplTitle,
													@RequestParam Department department) {
		logger.info("Message", "updateEmployee(" + idToUpdate + ", " + emplFirstName + ", " + 
													emplLastName + ", " + emplTitle + ", " +
													department.getDeptId() + ") called");
		return service.updateEmployee(idToUpdate, emplFirstName, emplLastName, emplTitle, department);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable int id) {
		logger.info("Message", "deleteEmployeeById(" + id + ") called");
		return service.deleteEmployeeById(id);
	}
}
