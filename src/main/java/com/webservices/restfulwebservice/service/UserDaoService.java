package com.webservices.restfulwebservice.service;

import com.webservices.restfulwebservice.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class UserDaoService {
    //JPA/Hibernate to interact with database
    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;
    static {
        users.add(new User(userCount++, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(userCount++, "Eve", LocalDate.now().minusYears(20)));
        users.add(new User(userCount++, "Jack", LocalDate.now().minusYears(10)));
        users.add(new User(userCount++, "Jill", LocalDate.now().minusYears(5)));
        users.add(new User(userCount++, "John", LocalDate.now().minusYears(19)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<User> predicate = user -> user.getId() == id;
        /*if (users.stream().noneMatch(predicate))
            return null;*/
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(userCount++);
        users.add(user);
        return user;

    }
}
