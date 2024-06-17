package com.restaurant.modern.resolver;

import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.repository.UsuarioRepository;
import com.restaurant.modern.security.TokenGenerator;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController

public class UsuarioMutationResolver implements GraphQLMutationResolver {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@MutationMapping
	public Usuario createUsuario(@Argument Usuario usuario) {
		if (!usuarioRepository.existsByEmail(usuario.getEmail())) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			if (usuario.getRole() == null) {
				usuario.setRole(Usuario.Role.USER);
			}
			return usuarioRepository.save(usuario);

		}
		throw new IllegalArgumentException("User already exists with ID : " + usuario.getId());
	}

	@MutationMapping
	@Secured("ADMIN")
	public Usuario updateUsuario(@Argument Usuario usuario) {
		LOGGER.info("Update request received: {}", usuario);

		Optional<Usuario> currentUserOpt = usuarioRepository.findById(usuario.getId());
		if (currentUserOpt.isPresent()) {
			Usuario currentUser = currentUserOpt.get();
			if (usuario.getNombre_usuario() != null) {
				currentUser.setNombre_usuario(usuario.getNombre_usuario());
			}
			if (usuario.getEmail() != null) {
				currentUser.setEmail(usuario.getEmail());
			}
			if (usuario.getPassword() != null) {
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
				currentUser.setPassword(usuario.getPassword());
			}
			if (usuario.getRole() != null) {
				currentUser.setRole(usuario.getRole());
			}

			return usuarioRepository.save(currentUser);
		} else {
			throw new IllegalArgumentException("No user found with id: " + usuario.getId());
		}
	}

	@MutationMapping
	@Secured("ADMIN")
	public boolean deleteUsuario(@Argument Long id) {
		LOGGER.info("Received request to delete user with id: {}", id);
		Optional<Usuario> currentUserOpt = usuarioRepository.findById(id);
		if (currentUserOpt.isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		} else {
			throw new IllegalArgumentException("No user found with id: " + id);
		}
	}

	@MutationMapping
	public String login(@Argument String email, @Argument String password) {
		Usuario user = usuarioRepository.findByEmail(email);

		if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
			throw new GraphQLException("Invalid credentials");
		}

		return tokenGenerator.build(user.getEmail(), user.getRole());
	}

}
