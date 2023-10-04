package com.webservices.restfulwebservice.repository;

import com.webservices.restfulwebservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
