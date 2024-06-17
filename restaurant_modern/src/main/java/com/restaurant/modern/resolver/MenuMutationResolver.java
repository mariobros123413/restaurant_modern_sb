package com.restaurant.modern.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.modern.entity.Bebida;
import com.restaurant.modern.entity.Menu;
import com.restaurant.modern.entity.Plato;
import com.restaurant.modern.service.MenuService;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@RestController
public class MenuMutationResolver implements GraphQLMutationResolver {
	@Autowired
	private MenuService menuService;

	@MutationMapping
	public Menu createMenu(@Argument("id_usuario") Long id_usuario, @Argument("fecha") String fecha,
			@Argument List<Plato> plato, @Argument List<Bebida> bebida) {
		return menuService.createMenu(id_usuario, fecha, plato, bebida);
	}

	@MutationMapping
	public Menu updateMenu(@Argument("id") String id, @Argument("fecha") String fecha,
			@Argument List<Plato> plato, @Argument List<Bebida> bebida) {
		return menuService.updateMenu(id, fecha, plato, bebida);
	}

	@MutationMapping
	public boolean deleteMenu(@Argument("id") String id) {
		menuService.deleteMenu(id);
		return true;
	}
}
