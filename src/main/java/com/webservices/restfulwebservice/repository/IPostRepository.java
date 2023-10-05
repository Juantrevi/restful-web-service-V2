package com.webservices.restfulwebservice.repository;

import com.webservices.restfulwebservice.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post, Integer> {
}
