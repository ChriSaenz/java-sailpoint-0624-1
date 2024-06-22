package com.skillstorm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
