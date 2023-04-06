package com.sbedev.employeeapi.controller;

import com.sbedev.employeeapi.exception.ResourceNotFoundException;
import com.sbedev.employeeapi.model.Employee;
import com.sbedev.employeeapi.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private static final Long EMPLOYEE_ID = 1L;
    private static final Employee EMPLOYEE = new Employee(EMPLOYEE_ID, "John", "Doe", "john.doe@example.com");

    @Mock  // @Mock(lenient = true)
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void createEmployee_ShouldReturnCreatedEmployee() {
        // given
        Employee newEmployee = new Employee(null, "Jane", "Doe", "jane.doe@example.com");
        given(employeeService.createEmployee(newEmployee)).willReturn(new Employee(2L, "Jane", "Doe", "jane.doe@example.com"));

        // when
        Employee result = employeeController.createEmployee(newEmployee);

        // then
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("jane.doe@example.com", result.getEmail());
    }

    @Test
    public void getEmployeeById_ShouldReturnEmployee_WhenEmployeeExists() {
        given(employeeService.getEmployeeById(EMPLOYEE_ID)).willReturn(Optional.of(EMPLOYEE));

        Optional<Employee> result = employeeController.getEmployeeById(EMPLOYEE_ID);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
    }

    @Test
    public void getEmployeeById_ShouldReturnEmpty_WhenEmployeeDoesNotExist() {
        // given
        given(employeeService.getEmployeeById(2L)).willReturn(Optional.empty());

        Optional<Employee> result = employeeController.getEmployeeById(2L);
        assertTrue(result.isEmpty());
    }

    @Test
    public void updateEmployee_ShouldReturnUpdatedEmployee() {
        given(employeeService.getEmployeeById(EMPLOYEE_ID)).willReturn(Optional.of(EMPLOYEE));
        // given
        Employee updatedEmployee = new Employee(EMPLOYEE_ID, "John", "Smith", "john.smith@example.com");
        given(employeeService.updateEmployee(updatedEmployee)).willReturn(updatedEmployee);

        // when
        Employee result = employeeController.updateEmployee(EMPLOYEE_ID, updatedEmployee);

        // then
        assertEquals("John", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("john.smith@example.com", result.getEmail());
    }

    @Test
    public void updateEmployee_ShouldThrowResourceNotFoundException_WhenEmployeeDoesNotExist() {
        // given
        given(employeeService.getEmployeeById(2L)).willReturn(Optional.empty());
        Employee updatedEmployee = new Employee(2L, "John", "Smith", "john.smith@example.com");

        // when / then
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeController.updateEmployee(2L, updatedEmployee);
        });
    }

    @Test
    public void deleteEmployee_ShouldReturnOk_WhenEmployeeExists() {
        // when
        ResponseEntity<Object> result = employeeController.deleteEmployee(EMPLOYEE_ID);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void deleteEmployee_ShouldThrowResourceNotFoundException_WhenEmployeeDoesNotExist() {

        doThrow(new ResourceNotFoundException("Employee not exist with id: 3")).when(employeeService).deleteEmployeeById(3L);

        assertThrows(ResourceNotFoundException.class, () -> {
            employeeController.deleteEmployee(3L);
        });
    }

    @Test
    public void getAllEmployees_ShouldReturnListOfEmployees() {
        //
        // given
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "John", "Doe", "john.doe@example.com"),
                new Employee(2L, "Jane", "Doe", "jane.doe@example.com"),
                new Employee(3L, "Bob", "Smith", "bob.smith@example.com")
        );
        given(employeeService.getAllEmployees()).willReturn(employees);

        // when
        List<Employee> result = employeeController.getAllEmployees();

        // then
        assertEquals(3, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals("john.doe@example.com", result.get(0).getEmail());
        assertEquals("Jane", result.get(1).getFirstName());
        assertEquals("Doe", result.get(1).getLastName());
        assertEquals("jane.doe@example.com", result.get(1).getEmail());
        assertEquals("Bob", result.get(2).getFirstName());
        assertEquals("Smith", result.get(2).getLastName());
        assertEquals("bob.smith@example.com", result.get(2).getEmail());
    }
}