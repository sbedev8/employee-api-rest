package com.sbedev.employeeapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sbedev.employeeapi.model.CommandeStatus;
import com.sbedev.employeeapi.repository.CommandeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/status")
public class CommandeStatusController {

    @Autowired
    CommandeStatusRepository commandeStatusRepository;

    @GetMapping
    public List<CommandeStatus> getAllClients() {
        return commandeStatusRepository.findAll();
    }


}
