package com.codecool.elproyectegrande.security;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Resource
    private UserDetailsService userDetailsService;

    private CustomAuthenticationManager authenticationManager;
    @Autowired
    public SecurityConfig(CustomAuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((authz) -> authz.requestMatchers("/**","/products","/products/** ","/frontend/**","/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(new AuthenticationFilter(authenticationManager))
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
}