package com.restaurant.modern.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.modern.entity.Factura;
@Repository
public interface FacturaRepository extends MongoRepository<Factura, String>{

}
