package com.restaurant.modern.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.restaurant.modern.service.UsuarioService;

import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityBeansConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioService(); // Asegúrate de que UserService implementa UserDetailsService
    }
}
