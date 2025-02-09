package com.esolution.reactive.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.esolution.reactive.model.Country;
import com.esolution.reactive.model.Location;

import reactor.core.publisher.Flux;

public interface LocationRepository extends ReactiveCrudRepository<Location, UUID> {
	Flux<Location> findByCity(String city);
	Flux<Location> findByCountry(Country country);
}
