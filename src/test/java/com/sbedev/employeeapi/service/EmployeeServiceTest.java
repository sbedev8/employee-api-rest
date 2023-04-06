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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("johndoe@example.com");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertEquals(employee, result);
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("johndoe@example.com");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertEquals(Optional.of(employee), result);
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("johndoe@example.com");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.updateEmployee(employee);

        assertEquals(employee, result);
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("johndoe@example.com");

        employeeService.deleteEmployee(employee);

        // Verify that the delete method was called with the employee object
        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setEmail("johndoe@example.com");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("Bob");
        employee2.setLastName("Smith");
        employee2.setEmail("bobsmith@example.com");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(employeeList, result);
    }
}
