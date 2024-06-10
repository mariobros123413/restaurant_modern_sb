package com.restaurant.modern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant.modern.entity.Factura;
import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario createUsuario(String nombreUsuario, String password, Boolean admin) {
		// Encriptar el password antes de guardarlo
		String encodedPassword = passwordEncoder.encode(password);
		Usuario user = new Usuario(nombreUsuario, encodedPassword, admin);
		return usuarioRepository.save(user);
	}

	public Usuario getUsuario(String id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
	}
	
	public Usuario updateUsuario(String id, String nombre_usuario, String password, Boolean isAdmin) {
		String encodedPassword = passwordEncoder.encode(password);
		Usuario usuario = getUsuario(id);
		usuario.setNombre_usuario(nombre_usuario);
		usuario.setPassword(encodedPassword);
		usuario.setAdmin(isAdmin);
		return usuarioRepository.save(usuario);
	}

	public void deleteUsuario(String id) {
		Usuario usuario= getUsuario(id);
		usuarioRepository.delete(usuario);
	}
}
