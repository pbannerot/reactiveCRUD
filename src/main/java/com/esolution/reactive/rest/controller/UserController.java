package com.esolution.reactive.rest.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esolution.reactive.model.User;
import com.esolution.reactive.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "API pour gérer les utilisateurs")
// http://localhost:8080/swagger-ui/index.html
public class UserController {

	@Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Récupérer tous les utilisateurs")
//    curl -X GET http://localhost:8080/users
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un utilisateur par ID")
//    curl -X GET http://localhost:8080/users/{id}
    public Mono<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel utilisateur")
//    curl -X POST http://localhost:8080/users \
//        -H "Content-Type: application/json" \
//        -d '{
//              "firstName": "John",
//              "lastName": "Doe",
//              "location": {
//                  "id": "e7e1f9f7-b3b3-4f0e-85e7-b3b34f0e85e7"
//              }
//            }'
    public Mono<User> createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Mise à jour d'un utilisateur")
//    curl -X PUT http://localhost:8080/users/{id} \
//        -H "Content-Type: application/json" \
//        -d '{
//              "firstName": "Jane",
//              "lastName": "Smith",
//              "locationId": "e7e1f9f7-b3b3-4f0e-85e7-b3b34f0e85e7"
//            }'
    public Mono<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur par ID")
//    curl -X DELETE http://localhost:8080/users/{id}
    public Mono<Void> deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}
