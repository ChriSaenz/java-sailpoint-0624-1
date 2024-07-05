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
	
	public ResponseEntity<Iterable<Employee>> getAllEmployees() {
		logger.info("getAllEmployees() resolved successfully");
		return ResponseEntity.status(200).header("Message", "Returned all employees")
				.body(repo.findAll());
	}
	
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
