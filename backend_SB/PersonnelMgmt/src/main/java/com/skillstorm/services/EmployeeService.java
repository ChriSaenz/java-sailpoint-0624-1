package com.skillstorm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.models.Department;
import com.skillstorm.models.Employee;
import com.skillstorm.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired private EmployeeRepository repo;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** getAllEmployees
	 * Makes a basic findAll() call to the database through the repository.
	 * @return ResponseEntity containing all employees in database table.
	 */
	public ResponseEntity<Iterable<Employee>> getAllEmployees() {
		logger.info("getAllEmployees() resolved successfully");
		return ResponseEntity.status(200).header("Message", "Returned all employees")
				.body(repo.findAll());
	}
	
	/** getEmployeeById
	 * Checks if the database has an employee with the designated id, then if it does, return that employee.
	 * @param id - Id of employee to get.
	 * @return ResponseEntity containing the Employee with corresponding id, or null if the operation failed.
	 */
	public ResponseEntity<Employee> getEmployeeById(int id) {
		if(!repo.existsById(id)) {
			logger.error("getEmployeeById() resulted in error: No Employee exists with id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("Error", "No Employee exists with id " + id).body(null);
		}
		logger.info("getEmployeeById() resolved successfully");
		return ResponseEntity.status(200).header("Message", "Returned all employees")
				.body(repo.findById(id).get());
	}
	
	/** addEmployee
	 * Checks if the database has an employee with the id of the employee to add, then if it does NOT, add the specified employee.
	 * @param employee - Object containing employee to add.
	 * @return ResponseEntity containing the added Employee, or null if the operation failed.
	 */
	public ResponseEntity<Employee> addEmployee(Employee employee) {
		if(repo.existsById(employee.getEmplId())) {
			logger.error("addEmployee() resulted in error: An Employee with id " + employee.getEmplId() + " already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.header("Message", "An Employee with id " + employee.getEmplId() + " already exists")
					.body(repo.findById(employee.getEmplId()).get());
		}
		try {
			logger.info("addEmployee() resolved successfully");
			return ResponseEntity.status(200).header("Message", "New Employee added")
					.body(repo.save(employee));

		} catch(Exception e) {
			logger.error("addEmployee() resulted in exception: " + e);
		}
		return ResponseEntity.status(500).header("Error", "An error occurred").body(null);
	}
	
	/** updateEmployee
	 * Checks if the database has an employee with the designated idToUpdate, then if it does, update it with the provided information.
	 * @param idToUpdate - Id of the Employee to update.
	 * @param emplFirstName - New employee first name.
	 * @param emplLastName - New employee last name.
	 * @param emplTitle - New employee title.
	 * @param department - New department.
	 * @return ResponseEntity containing the updated Employee, or null if the operation failed.
	 */
	public ResponseEntity<Employee> updateEmployee(int idToUpdate, String newFirstName,
			String newLastName, String newTitle, Department department) {
		if(!repo.existsById(idToUpdate)) {
			logger.error("updateEmployee() resulted in error: No Employee exists with id " + idToUpdate);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("Error", "No Employee exists with id " + idToUpdate).body(null);
		}
		logger.info("updateEmployee() resolved successfully");
		return ResponseEntity.status(200).header("Message", "Updated employee with id " + idToUpdate)
				.body(repo.save(new Employee(idToUpdate, newFirstName,
						newLastName, newTitle, department)));
	}
	
	/** deleteEmployee
	 * Checks if the database has an employee with the designated id, then if it does, delete it.
	 * @param id - Id of the Employee to delete.
	 * @return ResponseEntity containing the deletedEmployee, or null if operation failed.
	 */
	public ResponseEntity<Employee> deleteEmployeeById(int id) {
		if(!repo.existsById(id)) {
			logger.error("deleteEmployeeById() resulted in error: No Employee exists with id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("Error", "No Employee exists with id " + id).body(null);
		}
		Employee e = repo.findById(id).get();
		repo.deleteById(id);
		logger.info("deleteEmployeeById() resolved successfully");
		return ResponseEntity.status(200).header("Message", "Deleted employee with id " + id)
				.body(e);
	}

}
