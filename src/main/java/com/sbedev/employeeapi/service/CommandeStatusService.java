package com.sbedev.employeeapi.service;

import com.sbedev.employeeapi.dto.ClientDTO;
import com.sbedev.employeeapi.dto.CommandeDTO;
import com.sbedev.employeeapi.dto.CommandeStatusDTO;
import com.sbedev.employeeapi.model.Client;
import com.sbedev.employeeapi.model.CommandeStatus;
import com.sbedev.employeeapi.repository.CommandeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommandeStatusService {

    CommandeStatusRepository commandeStatusRepository;

    public CommandeStatusService(CommandeStatusRepository commandeStatusRepository) {
        this.commandeStatusRepository = commandeStatusRepository;
    }

    public List<CommandeStatusDTO> getAllCommandeStatuses() {
        return commandeStatusRepository.findAll().stream()
                .map(this::toDTO)
//                .map( st -> toDTO(st))
                .collect(Collectors.toList());
    }

    private CommandeStatusDTO toDTO(CommandeStatus commandeStatus) {
        List<CommandeDTO> commandeDTOs = commandeStatus.getCommandes().stream()
                .map(commande -> {
                    Client client = commande.getClient();
                    ClientDTO clientDTO = new ClientDTO(client.getId(), client.getNom(), client.getPrenom(), client.getEmail());
                    return new CommandeDTO(commande.getId(), commande.getDateCommande(), clientDTO);
                })
                .collect(Collectors.toList());
        return new CommandeStatusDTO(commandeStatus.getId(), commandeStatus.getStatus(), commandeDTOs);
    }

    public List<CommandeStatusDTO> getCommandesByStatus(long idstatus){
        List<CommandeStatus> commandesByStatus = this.commandeStatusRepository.findByStatusId(idstatus);
        return commandesByStatus.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
