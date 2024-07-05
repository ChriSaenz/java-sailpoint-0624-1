package com.skillstorm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.Employee;

/** EmployeeRepository
 * Basic repo for handling database calls to table employee.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	
}
