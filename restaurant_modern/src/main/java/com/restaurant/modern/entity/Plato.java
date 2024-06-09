package com.restaurant.modern.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plato")
public class Plato {
	@Id
	private String id;
	private Integer cantidad;
	private String nombre;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Plato(String id, Integer cantidad, String nombre) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.nombre = nombre;
	}
	public Plato() {
		// TODO Auto-generated constructor stub
	}
}
