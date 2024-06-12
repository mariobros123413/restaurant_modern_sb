package com.restaurant.modern.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import com.restaurant.modern.entity.Bebida;
import com.restaurant.modern.entity.Menu;
import com.restaurant.modern.entity.Plato;
import com.restaurant.modern.service.MenuService;

public class MenuController {
	private MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@MutationMapping
	public Menu crearMenu(String id_usuario,  String fecha, List<Plato> plato, List<Bebida> bebida) {
		return menuService.createMenu(id_usuario, fecha, plato, bebida);
	}

	@QueryMapping
	public List<?> getMenu() {
		return menuService.getAllMenus();
	}
}
