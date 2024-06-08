package com.restaurant.modern.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.restaurant.modern.entity.Factura;

public interface FacturaRepository extends MongoRepository<Factura, String>{

}
