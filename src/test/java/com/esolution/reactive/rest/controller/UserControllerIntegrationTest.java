package com.esolution.reactive.rest.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.esolution.reactive.model.Country;
import com.esolution.reactive.model.Location;
import com.esolution.reactive.model.User;
import com.esolution.reactive.repository.LocationRepository;
import com.esolution.reactive.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:test.properties")
@Slf4j
class UserControllerIntegrationTest {
	
	@LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    private User user;
    private Location location;
    
	@BeforeEach
	void setUp() throws Exception {
		userRepository.deleteAll().block();
		locationRepository.deleteAll().block();
//		userRepository.count().subscribe(u -> log.info("Nb users: " + u));
//		locationRepository.count().subscribe(l -> log.info("Nb locations: " + l));
		
		location = locationRepository.save(new Location.Builder()
				.city("Paris")
				.country(Country.FR)
				.build())
			.block();
		
		user = new User.Builder()
				.firstName("John")
				.lastName("Doe")
				.location(location)
				.build();
        userRepository.save(user).block();
	}

	@Test
	void testGetAllUsers() {
		userRepository.count().subscribe(u -> log.info("Nb users: " + u));
		locationRepository.count().subscribe(l -> log.info("Nb locations: " + l));
		
		webTestClient.get().uri("/users")
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(User.class)
			.hasSize(1)
			.contains(user);
	}

	@Test
	void testGetUserById() {
		webTestClient.get().uri("/users/{id}", user.getId())
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(User.class)
			.isEqualTo(user);
	}

	@Test
	void testCreateUser() {
		User newUser = new User.Builder()
				.firstName("Jane")
				.lastName("Smith")
				.location(location)
				.build();
		webTestClient.post().uri("/users").body(BodyInserters.fromValue(newUser))
			.exchange().expectStatus().isOk()
			.expectBody(User.class)
			.value(savedUser -> {
					assert savedUser != null;
					assert savedUser.getId() != null;
					assert "Jane".equals(savedUser.getFirstName());
				});
	}

	@Test
	void testUpdateUser() {
		user.setFirstName("Jane");

        webTestClient.put().uri("/users/{id}", user.getId())
                .body(BodyInserters.fromValue(user))
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .isEqualTo(user);
	}

	@Test
	void testDeleteUser() {
		webTestClient.delete().uri("/users/{id}", user.getId())
        	.exchange()
        	.expectStatus().isOk();

		webTestClient.get().uri("/users/{id}", user.getId())
        	.exchange()
        	.expectStatus().isNotFound();
	}

}
