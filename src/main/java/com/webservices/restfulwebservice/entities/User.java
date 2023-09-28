package com.webservices.restfulwebservice.entities;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jdk.jfr.Timestamp;

import java.time.LocalDate;

public class User {

    private int id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date cannot be in the future")
    private LocalDate birthDate;

    public User(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User() {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
