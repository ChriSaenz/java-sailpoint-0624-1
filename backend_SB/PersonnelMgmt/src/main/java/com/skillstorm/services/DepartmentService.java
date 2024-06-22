package com.skillstorm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.models.Department;
import com.skillstorm.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired private DepartmentRepository repo;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public ResponseEntity<Iterable<Department>> getAllDepartments() {
		return ResponseEntity.status(200).header("Message", "Returned all departments")
				.body(repo.findAll());
	}
}
