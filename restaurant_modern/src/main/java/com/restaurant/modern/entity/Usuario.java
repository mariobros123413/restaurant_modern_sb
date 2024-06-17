package com.restaurant.modern.entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;

@Document(collection = "usuario")
public class Usuario {
	@Id
	private String id;
    @Field("nombre_usuario")
	private String nombre_usuario;
    @Field("email")
    private String email;
	private String password;
	private boolean isAdmin;
	@ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
	
	public Usuario(String nombre_usuario,String email, String password, boolean isAdmin) {
		this.nombre_usuario = nombre_usuario;
		this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        }
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
