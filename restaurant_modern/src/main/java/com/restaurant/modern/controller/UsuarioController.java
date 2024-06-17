package com.restaurant.modern.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.service.UsuarioService;

@RestController
public class UsuarioController {

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	@MutationMapping
	public Usuario crearUsuario(String nombreUsuario, String email, String password, Boolean admin){
      
        //return usuarioRepo.save(usuario);
        return usuarioService.createUsuario(nombreUsuario, email,password, admin);

	}
	
	@QueryMapping
	public List<Usuario> getUsuario(){
	        return usuarioService.getAllUsuarios();
	}
}
