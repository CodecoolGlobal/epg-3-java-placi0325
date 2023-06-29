package com.codecool.elproyectegrande.security;

import com.codecool.elproyectegrande.dao.ClientDAO;
import com.codecool.elproyectegrande.dao.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    private ClientDAO clientDAO;
   // private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationManager(ClientDAO clientDAO /*,PasswordEncoder passwordEncoder*/) {
        this.clientDAO = clientDAO;
        //this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = /*passwordEncoder.encode(*/authentication.getCredentials().toString();
        System.out.println("itttttt" + password);

         UserDetails userDetails = loadUserByUsername(username);
        if (userDetails == null || !password.equals(userDetails.getPassword())) {
            throw new AuthenticationException("Invalid username or password") {
                // customize exception message or behavior if needed
            };
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

             Client client = clientDAO.findClientByClientName(username);
             if (client == null) {
                 throw new UsernameNotFoundException(username);
             }


             String role = client.getRole();
             List<SimpleGrantedAuthority> authorities = new ArrayList<>();
             authorities.add(new SimpleGrantedAuthority(role));

             User user = new User(client.getClientName(),client.getPassword(),authorities);
             return new MyUserPrincipal(user);
         }
}
