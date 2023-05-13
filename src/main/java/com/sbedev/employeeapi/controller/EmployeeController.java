package com.sbedev.employeeapi.controller;

import com.sbedev.employeeapi.exception.ResourceNotFoundException;
import com.sbedev.employeeapi.model.Employee;
import com.sbedev.employeeapi.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing employees.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    // l'injection de dépendances via le constructeur est une pratique recommandée en programmation orientée objet
    // car elle rend les dépendances explicites et facilite la testabilité et la maintenance du code.

    /**
     * Constructs a new instance of EmployeeController with the given EmployeeService.
     * @param employeeService the employee service to use.
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Creates a new employee.
     * @param employee the employee to create.
     * @return the created employee.
     */
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    /**
     * Retrieves an employee by their ID.
     * @param id the ID of the employee to retrieve.
     * @return an Optional containing the employee, or empty if not found.
     */
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    /**
     * Updates an existing employee.
     * @param employee the updated employee to save.
     * @return the updated employee.
     */
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updateEmployee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getLastName());
        updateEmployee.setEmail(employee.getEmail());

        return employeeService.updateEmployee(updateEmployee);
    }

    /**
     * Deletes an employee.
     * @param id the ID of the employee to delete.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a list of all employees.
     * @return a list of all employees.
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}

