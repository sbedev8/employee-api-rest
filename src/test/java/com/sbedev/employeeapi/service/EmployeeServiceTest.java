package com.sbedev.employeeapi.service;

import com.sbedev.employeeapi.model.Employee;
import com.sbedev.employeeapi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("johndoe@example.com");
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee result = employeeService.createEmployee(employee);
        assertEquals(employee, result);
    }

    @Test
    public void testGetEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Optional<Employee> expectedEmployee = employeeService.getEmployeeById(1L);
        assertEquals(expectedEmployee, Optional.of(employee));
    }

    @Test
    public void testUpdateEmployee() {
        employee.setEmail("johndoe@mail.com");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee expectedEmployee = employeeService.updateEmployee(employee);
        assertEquals(expectedEmployee, employee);
        assertEquals(expectedEmployee.getEmail(),"johndoe@maicl.com");
    }

    @Test
    public void testDeleteEmployee() {
        employeeService.deleteEmployee(employee);

        // Verify that the delete method was called with the employee object
        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    public void testGetAllEmployees() {

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("Bob");
        employee2.setLastName("Smith");
        employee2.setEmail("bobsmith@example.com");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(employeeList, result);
    }
}
