package com.sbedev.employeeapi.dto;

import java.util.List;

public record CommandeStatusDTO(Long id, String status, List<CommandeDTO> commandes) {}

