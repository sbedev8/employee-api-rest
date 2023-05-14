package com.sbedev.employeeapi.dto;

import java.time.LocalDate;

public record CommandeDTO(Long id, LocalDate dateCommande, Long status) {

}

