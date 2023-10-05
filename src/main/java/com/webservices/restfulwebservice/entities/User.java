package com.webservices.restfulwebservice.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jdk.jfr.Timestamp;

import java.time.LocalDate;

@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private int id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name should have at least 2 characters")
    //@JsonProperty("user_name")
    private String name;
    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date cannot be in the future")
    @Timestamp("dd-MM-yyyy")
    //@JsonProperty("birth_date")
    private LocalDate birthDate;
    //@JsonProperty("city")
    @ManyToOne
    private City city;

    public User() {
    }

    public User(int id, String name, LocalDate birthDate, City city) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", city=" + city +
                '}';
    }
}
