package com.sbedev.employeeapi.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents an employee in our system.
 */
public class Employee implements Serializable {

    /**
     * Unique identifier for the employee
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    /**
     * First name of the employee
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Last name of the employee
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Email address of the employee
     */
    private String email;

    /**
     * Gets the unique identifier for the employee.
     *
     * @return the unique identifier for the employee.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the employee.
     *
     * @param id the unique identifier for the employee to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the first name of the employee.
     *
     * @return the first name of the employee.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName the first name of the employee to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the employee.
     *
     * @return the last name of the employee.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName the last name of the employee to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the employee.
     *
     * @return the email address of the employee.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the employee.
     *
     * @param email the email address of the employee to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
