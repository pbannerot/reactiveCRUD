package com.esolution.reactive.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esolution.reactive.model.User;
import com.esolution.reactive.repository.LocationRepository;
import com.esolution.reactive.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private LocationRepository locationRepository;

	public Flux<User> getAllUsers() {
	    return userRepository.findAll()
	            .flatMap(user -> {
	                if (user.getLocationId() != null) {
	                    return locationRepository.findById(user.getLocationId())
	                            .doOnNext(user::setLocation)
	                            .thenReturn(user);
	                } else {
	                    return Mono.just(user);
	                }
	            });
	}

	public Mono<User> getUserById(UUID id) {
	    return userRepository.findById(id)
	            .flatMap(user -> {
	                if (user.getLocationId() != null) {
	                    return locationRepository.findById(user.getLocationId())
	                            .doOnNext(user::setLocation)
	                            .thenReturn(user);
	                } else {
	                    return Mono.just(user);
	                }
	            });
	}

    public Mono<User> saveUser(User user) {
        return userRepository.save(user)
                .flatMap(savedUser -> {
                    if (savedUser.getLocationId() != null) {
                        return locationRepository.findById(savedUser.getLocationId())
                                .doOnNext(savedUser::setLocation)
                                .thenReturn(savedUser);
                    } else {
                        return Mono.just(savedUser);
                    }
                });
    }

    public Mono<User> updateUser(UUID id, User updatedUser) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    existingUser.setLocationId(updatedUser.getLocationId());
                    return userRepository.save(existingUser)
                            .flatMap(savedUser -> {
                                if (savedUser.getLocationId() != null) {
                                    return locationRepository.findById(savedUser.getLocationId())
                                            .doOnNext(savedUser::setLocation)
                                            .thenReturn(savedUser);
                                } else {
                                    return Mono.just(savedUser);
                                }
                            });
                });
    }
    
    public Mono<Void> deleteUser(UUID id) {
        return userRepository.deleteById(id);
    }
    
}
