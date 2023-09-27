package com.webservices.restfulwebservice.service;

import com.webservices.restfulwebservice.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDaoService {
    //JPA/Hibernate to interact with database
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Eve", LocalDate.now().minusYears(20)));
        users.add(new User(3, "Jack", LocalDate.now().minusYears(10)));
        users.add(new User(4, "Jill", LocalDate.now().minusYears(5)));
        users.add(new User(5, "John", LocalDate.now().minusYears(19)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        for(User user: users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
