package com.sbedev.employeeapi.controller;

import com.sbedev.employeeapi.dto.CommandeStatusDTO;
import com.sbedev.employeeapi.service.CommandeStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/status")
public class CommandeStatusController {

    private final CommandeStatusService commandeStatusService;

    public CommandeStatusController(CommandeStatusService commandeStatusService) {
        this.commandeStatusService = commandeStatusService;
    }

    @GetMapping
    public List<CommandeStatusDTO> getAllCommandeStatus() {
        return commandeStatusService.getAllCommandeStatuses();
    }

//    @GetMapping
//    public List<CommandeStatus> getAllClients() {
//        return commandeStatusRepository.findAll();
//    }

    @GetMapping("/id/{id}")
    public CommandeStatusDTO getAllStatusById(@PathVariable long id){
        return commandeStatusService.getCommandesByStatus(id);
    }

    @GetMapping("/status/{status}")
    public CommandeStatusDTO getAllStatusByStatus(@PathVariable String status){
        return commandeStatusService.getCommandesByStatus(status);
    }


}
