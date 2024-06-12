package com.restaurant.modern.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menu")
public class Menu {
	@Id
	private String id;
    @DBRef
	private Usuario usuario;
	private List<Plato> plato;
	private List<Bebida> bebida;
	private String fecha;
	public Menu( Usuario usuario, List<Plato> plato, List<Bebida> bebida, String fecha) {
		super();
		this.usuario = usuario;
		this.plato = plato;
		this.bebida = bebida;
		this.fecha = fecha;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Plato> getPlato() {
		return plato;
	}
	public void setPlato(List<Plato> plato) {
		this.plato = plato;
	}
	public List<Bebida> getBebida() {
		return bebida;
	}
	public void setBebida(List<Bebida> bebida) {
		this.bebida = bebida;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}