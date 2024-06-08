package com.restaurant.modern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.repository.UsuarioRepository;
import com.restaurant.modern.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	@PostMapping
	public Usuario crearUsuario(@RequestBody Usuario usuario){
        System.out.println("Received Usuario: " + usuario.getNombre_usuario());
        //return usuarioRepo.save(usuario);
        return usuarioService.createUsuario(usuario);

	}
	
	@GetMapping
	public List<Usuario> getUsuario(){
	        return usuarioService.getAllUsuarios();
	}
}
