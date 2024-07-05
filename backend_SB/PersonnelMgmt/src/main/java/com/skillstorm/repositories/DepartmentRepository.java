package com.skillstorm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.models.Department;

/**	DepartmentRepository
 * Basic repo for handling database calls to table department.
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{

}
