package com.restaurant.modern.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.modern.entity.Usuario;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    @Query("{'nombre_usuario': ?0}")
    Optional<Usuario> findByNombre_usuario(String nombre_usuario);
    @Query("{'email': ?0}")
    Optional<Usuario> findByEmail(String email);
}
