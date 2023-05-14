package com.sbedev.employeeapi.repository;

import com.sbedev.employeeapi.model.CommandeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeStatusRepository extends JpaRepository<CommandeStatus, Long> {
}
