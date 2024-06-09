package com.restaurant.modern.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.restaurant.modern.entity.Mesa;

public interface MesaRepository extends MongoRepository<Mesa, String>{
    Optional<Mesa> findByNro(Integer nro);

}
