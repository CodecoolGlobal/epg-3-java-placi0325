package com.codecool.elproyectegrande.security;

import com.codecool.elproyectegrande.dao.ClientDAO;
import com.codecool.elproyectegrande.dao.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private ClientDAO clientDAO;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Client client  = clientDAO.findClientByClientName(username);
        if (client == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(client.getClientName()).password(client.getPassword()).authorities("USER").build();
        return user;
    }
}