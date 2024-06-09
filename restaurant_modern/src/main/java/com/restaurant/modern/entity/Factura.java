package com.restaurant.modern.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "factura")
public class Factura {
	@Id
	private String nro;
	private Usuario id_usuario;
	private Double total;
	private String fecha;
	private Pedido pedido;

	public Factura(Usuario id_usuario, Double total, String fecha, Pedido pedido) {
		super();
		this.id_usuario = id_usuario;
		this.total = total;
		this.fecha = fecha;
		this.pedido = pedido;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
