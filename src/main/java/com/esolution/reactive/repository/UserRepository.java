package com.esolution.reactive.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.esolution.reactive.model.User;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {


}
