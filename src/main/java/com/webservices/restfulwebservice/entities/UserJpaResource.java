package com.webservices.restfulwebservice.entities;


import com.webservices.restfulwebservice.exceptions.UserNotFoundException;
import com.webservices.restfulwebservice.repository.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {


    private final IUserRepository service;

    public UserJpaResource(IUserRepository service) {
        this.service = service;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }


    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = service.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id + " not found");
        }else {
            //EntityModel is a wrapper for a model class that adds links to it
            //This is a HATEOAS implementation
            //HATEOAS is a constraint of the REST application architecture
            //HATEOAS stands for Hypermedia as the Engine of Application State
            //HATEOAS is a component of the REST application architecture that
            //distinguishes it from other network application architectures
            //A REST client needs no prior knowledge about how to interact with
            //any particular application or server beyond a generic understanding of hypermedia
            EntityModel<User> entityModel = EntityModel.of(user.get());
            WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
            entityModel.add(link.withRel("all-users"));
            return entityModel;
        }
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public Optional<User> deleteUser(@PathVariable int id) {
        Optional<User> user = service.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id + " not found, cannot delete");
        }else {
            service.deleteById(id);
            return user;
        }
    }
}

