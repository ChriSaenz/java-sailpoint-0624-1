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

/**	EmployeeController
 * Controller for handling employee requests made by frontend.
 */
@RestController
@RequestMapping("/empl")
@CrossOrigin(origins = "*")
public class EmployeeController {
	@Autowired private EmployeeService service;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** getAllEmployees
	 * Calls EmployeeService to get all employees.
	 * @return ResponseEntity containing all employees in database table.
	 */
	@GetMapping
	public ResponseEntity<Iterable<Employee>> getAllEmployees() {
		logger.info("Message", "getAllEmployees() called");
		return service.getAllEmployees();
	}
	
	/** getEmployeeById
	 * Calls EmployeeService to return a specific employee by its id.
	 * @param id - Id of employee to get.
	 * @return ResponseEntity containing the Employee with corresponding id, or null if the operation failed.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		logger.info("Message", "getEmployeeById(" + id + ") called");
		return service.getEmployeeById(id);
	}
	
	/** addEmployee
	 * Calls EmployeeService to add a new employee.
	 * @param employee - Object containing employee to add.
	 * @return ResponseEntity containing the added Employee, or null if the operation failed.
	 */
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		logger.info("Message", "addEmployee(" + employee + ") called");
		return service.addEmployee(employee);
	}
	
	/** updateEmployee
	 * Calls EmployeeService to update an existing employee with specified information.
	 * @param idToUpdate - Id of the Employee to update.
	 * @param emplFirstName - New employee first name.
	 * @param emplLastName - New employee last name.
	 * @param emplTitle - New employee title.
	 * @param department - New department.
	 * @return ResponseEntity containing the updated Employee, or null if the operation failed.
	 */
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
	
	/** deleteEmployeeById
	 * Calls EmployeeService to delete an employee from the database.
	 * @param id - Id of the Employee to delete.
	 * @return ResponseEntity containing the deleted Employee, or null if the operation failed.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable int id) {
		logger.info("Message", "deleteEmployeeById(" + id + ") called");
		return service.deleteEmployeeById(id);
	}
}
