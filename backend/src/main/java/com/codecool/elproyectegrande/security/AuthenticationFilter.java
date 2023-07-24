package com.codecool.elproyectegrande.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.codecool.elproyectegrande.controller.dto.NewClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        try {
            byte[] inputStreamBytes = StreamUtils.copyToByteArray(req.getInputStream());
            NewClientDTO clientDTO = new ObjectMapper().readValue(inputStreamBytes, NewClientDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            clientDTO.clientName(),
                            clientDTO.password(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



            /*UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(req.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword(), new ArrayList<>());

            return authenticationManager.authenticate(authentication);*/


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        List<String> roles = new ArrayList<>(authorities.size());
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }


        String key = "lacinagyoneroskulcsalacinagyoneroskulcsalacinagyoneroskulcsa";
        Algorithm algorithm = Algorithm.HMAC256(key.getBytes());


        String token = JWT.create()
                .withSubject(String.valueOf(auth.getPrincipal()))
                .withClaim("roles", roles)
                .sign(algorithm);

        res.setHeader("Authorization", "Bearer " + token);
        System.out.println("SuccessFully authorized and authenticated with name:  " + auth.getPrincipal()
                + ", token: " + token + ", role: " + roles.get(0));
    }
}
