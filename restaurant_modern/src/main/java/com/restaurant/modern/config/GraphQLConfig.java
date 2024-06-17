package com.restaurant.modern.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurant.modern.resolver.FacturaMutationResolver;
import com.restaurant.modern.resolver.FacturaQueryResolver;
import com.restaurant.modern.resolver.MenuMutationResolver;
import com.restaurant.modern.resolver.MenuQueryResolver;
import com.restaurant.modern.resolver.MesaMutationResolver;
import com.restaurant.modern.resolver.MesaQueryResolver;
import com.restaurant.modern.resolver.UsuarioMutationResolver;
import com.restaurant.modern.resolver.UsuarioQueryResolver;

@Configuration
public class GraphQLConfig {
	@Bean
	UsuarioMutationResolver usuarioMutationResolver() {
		return new UsuarioMutationResolver();
	}

	@Bean
	UsuarioQueryResolver usuarioQueryResolver() {
		return new UsuarioQueryResolver();
	}
	
	@Bean
	FacturaQueryResolver facturaQueryResolver() {
		return new FacturaQueryResolver();
	}

	@Bean
	FacturaMutationResolver facturaMutationResolver() {
		return new FacturaMutationResolver();
	}
	
	@Bean
	MenuQueryResolver menuQueryResolver () {
		return new MenuQueryResolver();
	}

	@Bean
	MenuMutationResolver menuMutationResolver() {
		return new MenuMutationResolver();
	}
	
	@Bean
	MesaMutationResolver mesaMutationResolver() {
		return new MesaMutationResolver();
	}
	
	@Bean
	MesaQueryResolver mesaQueryResolver() {
		return new MesaQueryResolver();
	}
}
