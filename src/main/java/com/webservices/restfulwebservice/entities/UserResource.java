package com.webservices.restfulwebservice.entities;

import com.webservices.restfulwebservice.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;
    public UserResource(UserDaoService service) {
        this.service = service;
    }

    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }
}
