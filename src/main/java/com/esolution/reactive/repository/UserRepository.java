package com.esolution.reactive.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.esolution.reactive.model.Location;
import com.esolution.reactive.model.User;

import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {
	Flux<Location> findByLocationId(UUID locationId);
}
