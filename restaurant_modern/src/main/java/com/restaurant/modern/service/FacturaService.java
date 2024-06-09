package com.restaurant.modern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.modern.entity.Factura;
import com.restaurant.modern.entity.Pedido;
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

	public Factura createFactura(String id_usuario, Double total, String fecha, Pedido pedido ) {

		// Buscar el usuario en la base de datos
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id_usuario);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			Factura facturac = new Factura(usuario, total, fecha, pedido);

			System.out.println("Entregando --------------");
			return facturaRepository.save(facturac);
		} else {
			// Manejar el caso cuando el usuario no se encuentra
			throw new IllegalArgumentException("Usuario no encontrado con ID: " + id_usuario);
		}
	}

	public Factura getFactura(String nro) {
		Optional<Factura> optionalFactura = facturaRepository.findById(nro);
		if (optionalFactura.isPresent()) {
			return optionalFactura.get();
		} else {
			// Manejar el caso cuando la factura no se encuentra
			throw new IllegalArgumentException("Factura no encontrada con número: " + nro);
		}
	}

}
