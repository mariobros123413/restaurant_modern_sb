package com.restaurant.modern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.modern.entity.Mesa;
import com.restaurant.modern.entity.Usuario;
import com.restaurant.modern.repository.MesaRepository;
import com.restaurant.modern.repository.UsuarioRepository;

@Service
public class MesaService {
	@Autowired
	private MesaRepository mesaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Mesa> getAllMesas() {
		return mesaRepository.findAll();
	}

	public Mesa createMesa(String id_usuario, Integer nro, Integer capacidad, Boolean disponible) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id_usuario);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			Mesa mesac = new Mesa(nro, capacidad, disponible, usuario);

			System.out.println("Entregando MESA --------------");
			return mesaRepository.save(mesac);
		} else {
			throw new IllegalArgumentException("Usuario no encontrado con ID: " + id_usuario);
		}
	}

	public Mesa getMesa(String id) {
		return mesaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
	}

	public Mesa getMesaByNro(Integer nro) {
		return mesaRepository.findByNro(nro)
				.orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada con número: " + nro));

	}
}
