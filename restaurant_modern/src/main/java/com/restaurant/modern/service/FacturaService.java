package com.restaurant.modern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.modern.entity.Factura;
import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.repository.FacturaRepository;
import com.restaurant.modern.repository.UsuarioRepository;

@Service
public class FacturaService {
	@Autowired
	private FacturaRepository facturaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<?> getAllFacturas() {
		return facturaRepository.findAll();
	}

	public Factura createFactura(Factura factura, String id_usuario) {

		// Buscar el usuario en la base de datos
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id_usuario);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			factura.setId_usuario(usuario);
			System.out.println("Entregando --------------");
			return facturaRepository.save(factura);
		} else {
			// Manejar el caso cuando el usuario no se encuentra
			throw new IllegalArgumentException("Usuario no encontrado con ID: " + id_usuario);
		}
	}
	
	//public getFacturaById(String nro)
}
