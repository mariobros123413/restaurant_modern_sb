package com.restaurant.modern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.dao.DuplicateKeyException;
import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
		Usuario usuario = usuarioOptional
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
		return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(),
				new ArrayList<>());
	}

	

	public Usuario createUsuario(String nombreUsuario, String email, String password, Boolean admin) {
	    try {
	        String encodedPassword = passwordEncoder.encode(password);
	        Optional<Usuario> userSearch = usuarioRepository.findByEmail(email);

	        if (userSearch.isPresent()) {
	            throw new IllegalStateException("El correo electrónico ya está registrado.");
	        }

	        Usuario user = new Usuario(nombreUsuario, email, encodedPassword, admin);
	        return usuarioRepository.save(user);
	    } catch (DuplicateKeyException e) {
	        throw new IllegalStateException("El correo electrónico ya está registrado.", e);
	    }
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
		Usuario usuario = getUsuario(id);
		usuarioRepository.delete(usuario);
	}
}
