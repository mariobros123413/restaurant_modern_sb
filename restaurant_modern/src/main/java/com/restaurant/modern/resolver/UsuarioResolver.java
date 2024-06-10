package com.restaurant.modern.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.graphql.data.method.annotation.MutationMapping;

import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.service.UsuarioService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class UsuarioResolver implements GraphQLQueryResolver {
	@Autowired
	private UsuarioService usuarioService;

	@QueryMapping
	public List<Usuario> getUsuarios() {
		return usuarioService.getAllUsuarios();
	}

	@QueryMapping
	public Usuario getUsuario(String id) {
		return usuarioService.getUsuario(id);
	}

	@MutationMapping
	public Usuario createUsuario(String nombreUsuario, String password, Boolean isAdmin) {
		return usuarioService.createUsuario(nombreUsuario, password, isAdmin);
	}

	@MutationMapping
	public Usuario updateUsuario(String id, String nombre_usuario, String password, Boolean isAdmin) {
		return usuarioService.updateUsuario(id, nombre_usuario, password, isAdmin);
	}

	@MutationMapping
	public boolean deleteUsuario(String id) {
		usuarioService.deleteUsuario(id);
		return true;
	}
}
