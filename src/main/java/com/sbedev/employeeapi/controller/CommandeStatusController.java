package com.sbedev.employeeapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sbedev.employeeapi.dto.CommandeStatusDTO;
import com.sbedev.employeeapi.model.CommandeStatus;
import com.sbedev.employeeapi.repository.CommandeStatusRepository;
import com.sbedev.employeeapi.service.CommandeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/status")
public class CommandeStatusController {

    private final CommandeStatusService commandeStatusService;

    public CommandeStatusController(CommandeStatusService commandeStatusService) {
        this.commandeStatusService = commandeStatusService;
    }

    @GetMapping
    public List<CommandeStatusDTO> getAllCommandeStatuses() {
        return commandeStatusService.getAllCommandeStatuses();
    }

//    @GetMapping
//    public List<CommandeStatus> getAllClients() {
//        return commandeStatusRepository.findAll();
//    }


}
