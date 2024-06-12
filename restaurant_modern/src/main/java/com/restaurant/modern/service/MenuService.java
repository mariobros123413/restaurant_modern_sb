package com.restaurant.modern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.modern.entity.Bebida;
import com.restaurant.modern.entity.Menu;
import com.restaurant.modern.entity.Plato;
import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.repository.MenuRepository;
import com.restaurant.modern.repository.UsuarioRepository;

@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<?> getAllMenus() {
		return menuRepository.findAll();
	}

	public Menu createMenu(String id_usuario, String fecha, List<Plato> plato, List<Bebida> bebida) {

		// Buscar el usuario en la base de datos
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id_usuario);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			Menu menuc = new Menu(usuario, plato, bebida, fecha);
			return menuRepository.save(menuc);
		} else {
			throw new IllegalArgumentException("Usuario no encontrado con ID: " + id_usuario);
		}
	}

	public Menu getMenu(String nro) {
		Optional<Menu> optionalMenu = menuRepository.findById(nro);
		if (optionalMenu.isPresent()) {
			return optionalMenu.get();
		} else {
			// Manejar el caso cuando la menu no se encuentra
			throw new IllegalArgumentException("Menu no encontrada con número: " + nro);
		}
	}
	
	public Menu updateMenu(String nro, String fecha, List<Plato> plato, List<Bebida> bebida) {
		Menu menu = getMenu(nro);
		menu.setPlato(plato);
		menu.setBebida(bebida);
		menu.setFecha(fecha);
		return menuRepository.save(menu);
	}

	public void deleteMenu(String nro) {
		Menu menu= getMenu(nro);
		menuRepository.delete(menu);
	}
}
