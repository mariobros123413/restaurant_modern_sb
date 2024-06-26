package com.restaurant.modern.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.restaurant.modern.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

	Usuario findByEmail(String email);

	Boolean existsByEmail(String email);
}
