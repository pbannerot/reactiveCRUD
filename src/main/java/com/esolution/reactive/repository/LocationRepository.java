package com.esolution.reactive.repository;

import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.esolution.reactive.model.Location;

public interface LocationRepository extends ReactiveCrudRepository<Location, UUID> {

}
