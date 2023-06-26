package com.codecool.elproyectegrande.controller;

import com.codecool.elproyectegrande.controller.dto.NewClientDTO;
import com.codecool.elproyectegrande.dao.model.Client;
import com.codecool.elproyectegrande.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientByID(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public ResponseEntity addNewClient(@RequestBody NewClientDTO clientDTO) {
        clientService.addNewClient(clientDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public void updateClientById(@PathVariable("id") Long id, @RequestBody Client updatedClient) {
        clientService.updateClientById(id, updatedClient);
    }

    @DeleteMapping("/{id}")
    public void deleteClientById(@PathVariable("id") Long id){
        clientService.deleteClientById(id);
    }

}