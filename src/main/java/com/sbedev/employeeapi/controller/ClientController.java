package com.sbedev.employeeapi.controller;

import com.sbedev.employeeapi.model.Client;
import com.sbedev.employeeapi.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }


    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable  Long id) {
        return Optional.ofNullable(clientService.getClientById(id));
    }



}
