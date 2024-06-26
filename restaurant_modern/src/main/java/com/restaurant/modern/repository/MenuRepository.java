package com.restaurant.modern.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.modern.entity.Menu;

@Repository
public interface MenuRepository extends MongoRepository<Menu, String>{
	Optional<Menu> findById(String id);
}
