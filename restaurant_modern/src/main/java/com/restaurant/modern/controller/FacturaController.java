package com.restaurant.modern.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.modern.entity.Factura;
import com.restaurant.modern.service.FacturaService;

@RestController
public class FacturaController {//

	private FacturaService facturaService;

	public FacturaController(FacturaService facturaService) {
		this.facturaService = facturaService;
	}

	@MutationMapping
	public Factura crearFactura(String id_usuario, Double total, String fecha) {
		return facturaService.createFactura(id_usuario, total, fecha);
	}

	@QueryMapping
	public List<?> getFactura() {
		return facturaService.getAllFacturas();
	}
}
