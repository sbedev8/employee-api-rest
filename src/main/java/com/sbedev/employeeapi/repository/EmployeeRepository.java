package com.sbedev.employeeapi.repository;

import com.sbedev.employeeapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Employee Repository interface for managing Employee entities in the database.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
