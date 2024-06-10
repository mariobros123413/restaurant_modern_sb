package com.restaurant.modern.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import com.restaurant.modern.entity.Mesa;
import com.restaurant.modern.service.MesaService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class MesaResolver implements GraphQLQueryResolver {
	@Autowired
	private MesaService mesaService;

	@QueryMapping
	public List<?> getMesas() {
		return mesaService.getAllMesas();
	}

	@QueryMapping
	public Mesa getMesa(String id) {
		return mesaService.getMesa(id);
	}
	
	@QueryMapping
	public Mesa getMesaByNro(Integer nro) {
		return mesaService.getMesaByNro(nro);
	}

	@QueryMapping
	public Mesa createMesa(String id_usuario, Integer nro, Integer capacidad, Boolean disponible) {

		return mesaService.createMesa(id_usuario, nro, capacidad, disponible);
	}
	
	@MutationMapping
    public Mesa updateMesa(String id, Integer nro, Integer capacidad, Boolean disponible) {
        return mesaService.updateMesa(id, nro, capacidad, disponible);
    }
	
	@MutationMapping
    public Mesa updateMesaByNro(Integer nro, Integer capacidad, Boolean disponible) {
        return mesaService.updateMesaByNro(nro, capacidad, disponible);
    }

    @MutationMapping
    public boolean deleteMesa(String id) {
        mesaService.deleteMesa(id);
        return true;
    }
}
