package com.sbedev.employeeapi.service;

import com.sbedev.employeeapi.exception.ResourceNotFoundException;
import com.sbedev.employeeapi.model.Employee;
import com.sbedev.employeeapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing employees.
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Creates a new employee.
     * @param employee the employee to create.
     * @return the created employee.
     */
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Retrieves an employee by their ID.
     * @param id the ID of the employee to retrieve.
     * @return an Optional containing the employee, or empty if not found.
     */
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Updates an existing employee.
     * @param employee the updated employee to save.
     * @return the updated employee.
     */
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Deletes an employee.
     * @param employee the employee to delete.
     */
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    /**
     * Deletes an employee.
     * @param id the employee id to delete.
     */
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        employeeRepository.delete(employee);
    }

    /**
     * Retrieves a list of all employees.
     * @return a list of all employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
