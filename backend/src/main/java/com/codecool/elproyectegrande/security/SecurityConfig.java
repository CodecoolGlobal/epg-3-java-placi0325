package com.codecool.elproyectegrande.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    @Autowired
    public SecurityConfig(CustomAuthenticationManager authenticationManager,PasswordEncoder passwordEncoder) {
        this.authenticationManager=authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST,"/products","/frontend/src/Components/NewProductForm.jsx").hasAnyAuthority("USER","ADMIN")
                        .requestMatchers("/**","/products","/products/** ","/frontend/**","/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(new AuthenticationFilter(authenticationManager));

        return http.build();
    }
}