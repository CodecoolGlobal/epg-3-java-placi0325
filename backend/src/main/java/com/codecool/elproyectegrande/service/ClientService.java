package com.codecool.elproyectegrande.service;


import com.codecool.elproyectegrande.controller.dto.NewClientDTO;
import com.codecool.elproyectegrande.dao.ClientDAO;
import com.codecool.elproyectegrande.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codecool.elproyectegrande.dao.model.Client;

import java.util.List;

@Service
public class ClientService{

    private ClientDAO clientDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientDAO clientDAO, PasswordEncoder passwordEncoder) {
        this.clientDAO = clientDAO;
        this.passwordEncoder = passwordEncoder;
    }


    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }


    public Client getClientById(Long id) {
        return clientDAO.findClientById(id);
    }



    public void addNewClient(NewClientDTO client) {
        System.out.println(client);
        Client newClient = Client.builder()
                .clientName(client.clientName())
                .password(passwordEncoder.encode(client.password()))
                .role(Role.USER.toString())
                .build();
        clientDAO.save(newClient);
    }


    public void updateClientById(Long id, Client updateClient){
        Client currentClient = getClientById(id);
        if (updateClient.getClientName() != null) currentClient.setClientName(updateClient.getClientName());
        if (updateClient.getPassword() != null) currentClient.setPassword(updateClient.getPassword());
    }

    public void deleteClientById(Long id){
        clientDAO.deleteById(id);
    }

}
