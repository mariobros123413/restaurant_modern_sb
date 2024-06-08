package com.restaurant.modern.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.modern.entity.Factura;
import com.restaurant.modern.service.FacturaService;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {//

	private FacturaService facturaService;

	public FacturaController(FacturaService facturaService) {
		this.facturaService = facturaService;
	}

	@PostMapping(value = "/{id}")
	public Factura crearFactura(@RequestBody Factura factura, @PathVariable("id") String id_usuario) {
		return facturaService.createFactura(factura, id_usuario);
	}

	@GetMapping
	public List<?> getFactura() {
		return facturaService.getAllFacturas();
	}
}
