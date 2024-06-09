package com.restaurant.modern.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedido")
public class Pedido {
	@Id
	private String nro_pedido;
	private String id_mesero;
	private int nro_mesa;
	private String nombre_comensal;
	private String fecha;
	private String hora;
	private Boolean estado;
	private List<Plato> plato;
	private List<Bebida> bebida;
	private String extras;

	public Pedido(String nro_pedido, String id_mesero, Integer nro_mesa, String nombre_comensal, String fecha,
			String hora, Boolean estado, List<Plato> plato, List<Bebida> bebida, String extras) {
		super();
		this.nro_pedido = nro_pedido;
		this.id_mesero = id_mesero;
		this.nro_mesa = nro_mesa;
		this.nombre_comensal = nombre_comensal;
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.plato = plato;
		this.bebida = bebida;
		this.extras = extras;
	}

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public String getNro_pedido() {
		return nro_pedido;
	}

	public void setNro_pedido(String nro_pedido) {
		this.nro_pedido = nro_pedido;
	}

	public String getId_mesero() {
		return id_mesero;
	}

	public void setId_mesero(String id_mesero) {
		this.id_mesero = id_mesero;
	}

	public int getNro_mesa() {
		return nro_mesa;
	}

	public void setNro_mesa(int nro_mesa) {
		this.nro_mesa = nro_mesa;
	}

	public String getNombre_comensal() {
		return nombre_comensal;
	}

	public void setNombre_comensal(String nombre_comensal) {
		this.nombre_comensal = nombre_comensal;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public List<Plato> getPlato() {
		return plato;
	}

	public void setPlato(List<Plato> platos) {
		this.plato = platos;
	}

	public List<Bebida> getBebida() {
		return bebida;
	}

	public void setBebida(List<Bebida> bebida) {
		this.bebida = bebida;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

}
