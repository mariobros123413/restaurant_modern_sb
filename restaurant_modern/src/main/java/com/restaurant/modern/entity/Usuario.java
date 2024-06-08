package com.restaurant.modern.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
public class Usuario {
	@Id
	private String id;
	private String nombre_usuario;
	private String password;
	private boolean isAdmin;
	
	public Usuario(String nombre_usuario, String password, boolean isAdmin) {this.nombre_usuario = nombre_usuario;
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
	
}
