package com.skillstorm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
