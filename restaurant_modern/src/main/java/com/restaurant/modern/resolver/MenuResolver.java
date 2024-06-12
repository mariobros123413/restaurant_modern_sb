package com.restaurant.modern.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import com.restaurant.modern.entity.Bebida;
import com.restaurant.modern.entity.Menu;
import com.restaurant.modern.entity.Plato;
import com.restaurant.modern.service.MenuService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class MenuResolver implements GraphQLQueryResolver {
	@Autowired
	private MenuService menuService;

	@QueryMapping
	public List<?> getMenus() {
		return menuService.getAllMenus();
	}

	@QueryMapping
	public Menu getMenu(String id) {
		return menuService.getMenu(id);
	}

	@QueryMapping
	public Menu createMenu(String id_usuario, String fecha, List<Plato> platoInput, List<Bebida> bebidaInput) {
		return menuService.createMenu(id_usuario, fecha, platoInput, bebidaInput);
	}

	@MutationMapping
	public Menu updateMenu(String id, String fecha, List<Plato> platoInput, List<Bebida> bebidaInput) {
		return menuService.updateMenu( id, fecha, platoInput, bebidaInput);
	}

	@MutationMapping
	public boolean deleteMenu(String id) {
		menuService.deleteMenu(id);
		return true;
	}
}
