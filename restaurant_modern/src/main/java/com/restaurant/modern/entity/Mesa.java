package com.restaurant.modern.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mesa")
public class Mesa {
	@Id
	private String id;
	private Integer nro;
	private Integer capacidad;
	private Boolean disponible;
	private Usuario usuario;

	public Mesa(Integer nro, Integer capacidad, Boolean disponible, Usuario usuario) {
		super();
		this.nro = nro;
		this.capacidad = capacidad;
		this.disponible = disponible;
		this.usuario = usuario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNro() {
		return nro;
	}

	public void setNro(Integer nro) {
		this.nro = nro;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
