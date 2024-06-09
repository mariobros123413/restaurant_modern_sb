package com.restaurant.modern.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bebida")
public class Bebida {
	@Id
	private String id;
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
	public Bebida(String id, Integer cantidad, String nombre) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.nombre = nombre;
	}
	public Bebida() {
		// TODO Auto-generated constructor stub
	}
	private Integer cantidad;
	private String nombre;
}
