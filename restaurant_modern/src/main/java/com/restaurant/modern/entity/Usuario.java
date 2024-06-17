package com.restaurant.modern.entity;

import java.util.Optional;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;

@Document(collection = "usuario")
public class Usuario {
	@Id
	private Long id;
	@Field("nombre_usuario")
	private String nombre_usuario;
	@Field("email")
	private String email;
	private String password;
	private Role role;

	public Usuario(String nombre_usuario, String email, String password, Role role) {
		this.id = new Random().nextLong();
		this.nombre_usuario = nombre_usuario;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public Usuario() {
		this.id = new Random().nextLong();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario merge(Usuario updateRequest) {
		Optional.ofNullable(updateRequest.getNombre_usuario()).ifPresent(this::setNombre_usuario);
		Optional.ofNullable(updateRequest.getEmail()).ifPresent(this::setEmail);
		Optional.ofNullable(updateRequest.getPassword()).ifPresent(this::setPassword);
		Optional.ofNullable(updateRequest.getRole()).ifPresent(this::setRole);

		return this;
	}

	public enum Role implements GrantedAuthority {
		USER, ADMIN;

		@Override
		public String getAuthority() {
			return this.name();
		}
	}

}
