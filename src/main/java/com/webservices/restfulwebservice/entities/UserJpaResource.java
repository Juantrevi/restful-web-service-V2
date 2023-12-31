package com.webservices.restfulwebservice.entities;


import com.webservices.restfulwebservice.exceptions.UserNotFoundException;
import com.webservices.restfulwebservice.repository.IPostRepository;
import com.webservices.restfulwebservice.repository.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class UserJpaResource {

    private final IUserRepository userService;
    private final IPostRepository postService;

    public UserJpaResource(IUserRepository service, IPostRepository postService) {
        this.userService = service;
        this.postService = postService;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }


/*    @GetMapping("/jpa/users/{id}")
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
    }*/

    @GetMapping("/jpa/users/{id}")
    public Optional<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id + " not found");
        }else {

            return user;
        }
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public Optional<User> deleteUser(@PathVariable int id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id + " not found, cannot delete");
        }else {
            userService.deleteById(id);
            return user;
        }
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPostsOfUser(@PathVariable int id) {
        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id + " not found");
        }else {
            return user.get().getPosts();
        }
    }

    @GetMapping("/jpa/users/posts")
    public List<Post> retrieveAllPosts() {
        return postService.findAll();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @Valid @RequestBody Post post) {

        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id + " not found");
        }else {

            post.setUser(user.get());
            Post savedPost = postService.save(post);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedPost.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        }
    }

    @GetMapping("/jpa/users/{id}/posts/{postId}")
    public Post retrievePost(@PathVariable int id, @PathVariable int postId) {
        Optional<User> user = userService.findById(id);
        Optional<Post> post = postService.findById(postId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id + " not found");
        }else {
            if (post.isEmpty()) {
                throw new UserNotFoundException("id:" + postId + " not found");
            }else if (post.get().getUser().getId() != id) {
                throw new UserNotFoundException("This post does not belong to this user");
            }else {
                return post.get();
            }
        }
    }

}

