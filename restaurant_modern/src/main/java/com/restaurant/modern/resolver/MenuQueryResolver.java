package com.restaurant.modern.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.modern.entity.Menu;
import com.restaurant.modern.service.MenuService;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@RestController
public class MenuQueryResolver implements GraphQLQueryResolver {
	@Autowired
	private MenuService menuService;

	@QueryMapping
	public List<Menu> getAllMenus() {
		return menuService.getAllMenus();
	}

	@QueryMapping
	public Menu getMenu(@Argument("id") String id) {
		return menuService.getMenu(id);
	}
}
