package com.restaurant.modern.resolver;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.filter.JwtUtils;
import com.restaurant.modern.service.UsuarioService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class UsuarioResolver implements GraphQLQueryResolver {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private final PasswordEncoder passwordEncoder;

    private static final SecretKey SECRET_KEY = JwtUtils.getSecretKey();

	public UsuarioResolver(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@QueryMapping
    //@Secured("ROLE_ADMIN")
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

	@MutationMapping
	public String login(@Argument String username, @Argument String password) {

		UserDetails userDetails = usuarioService.loadUserByUsername(username);

		if (passwordEncoder.matches(password, userDetails.getPassword())) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return generateToken(authentication); // Retorna el JWT token
		} else {
			throw new BadCredentialsException("Credenciales incorrectas");
		}
	}

	private String generateToken(@Argument Authentication authentication) {
		final long EXPIRATION_TIME = 900000; // 15 minutes in milliseconds

		String username = ((UserDetails) authentication.getPrincipal()).getUsername();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
		
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toSet());
		
		String token = Jwts.builder().setIssuer("Stormpath").setSubject(username).claim("roles", roles)
				.setIssuedAt(now).setExpiration(expiryDate).signWith(SECRET_KEY, SignatureAlgorithm.HS512)
				.compact();
		System.out.println(" TOKEN ----------------------" + token);
		return token;
	}

}
