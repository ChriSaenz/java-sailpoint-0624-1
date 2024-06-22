package com.skillstorm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{

}
