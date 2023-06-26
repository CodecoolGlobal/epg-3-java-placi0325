package com.codecool.elproyectegrande.dao.user;

import com.codecool.elproyectegrande.dao.model.Client;
import com.codecool.elproyectegrande.dao.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Long> {
    Client findClientById(Long id);
}
