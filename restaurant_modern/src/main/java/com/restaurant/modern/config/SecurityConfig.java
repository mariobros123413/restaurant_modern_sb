package com.restaurant.modern.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            /*.authorizeHttpRequests((requests) -> requests
                .anyRequest().permitAll()
            );*/
        	.csrf().disable()
        	.authorizeHttpRequests((requests) -> requests
                    .anyRequest().permitAll() // Permitir acceso a todas las URL sin autenticación
                )
        	.formLogin().permitAll()
        	.and()
        	.httpBasic();
        	
        return http.build();
    }
	
	@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("jose")
            .password("2025")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
