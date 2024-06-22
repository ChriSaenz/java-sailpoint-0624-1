package com.skillstorm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.models.Employee;
import com.skillstorm.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired private EmployeeRepository repo;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ResponseEntity<Iterable<Employee>> getAllEmployees() {
		return ResponseEntity.status(200).header("Message", "Returned all employees")
				.body(repo.findAll());
	}
}
