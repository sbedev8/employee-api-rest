package com.sbedev.employeeapi.repository;

import com.sbedev.employeeapi.model.CommandeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeStatusRepository extends JpaRepository<CommandeStatus, Long> {
    List<CommandeStatus> findByStatusId(Long idstatus);

}
