package com.sbedev.employeeapi.repository;

import com.sbedev.employeeapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
