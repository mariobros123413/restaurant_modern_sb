package com.restaurant.modern.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.modern.entity.Mesa;
import com.restaurant.modern.service.MesaService;

@RestController
public class MesaController {
	private MesaService mesaService;

	public MesaController(MesaService mesaService) {
		this.mesaService = mesaService;
	}

	@MutationMapping
	public Mesa crearMesa(String id_usuario, Integer nro, Integer capacidad, Boolean disponible) {
		return mesaService.createMesa(id_usuario, nro, capacidad, disponible);
	}

	@QueryMapping
	public List<?> getMesas() {
		return mesaService.getAllMesas();
	}

	@QueryMapping
	public Mesa getMesaByNro(Integer nro) {
		return mesaService.getMesaByNro(nro);
	}

	@QueryMapping
	public Mesa getMesa(String id) {
		return mesaService.getMesa(id);
	}
}
