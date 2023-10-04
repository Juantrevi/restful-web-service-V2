package com.webservices.restfulwebservice.repository;

import com.webservices.restfulwebservice.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City, Integer> {


}
