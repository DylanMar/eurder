package com.switchfully.eurder.repository;

import com.switchfully.eurder.entity.Role;
import com.switchfully.eurder.entity.User;
import com.switchfully.eurder.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {


    private static final Map<UUID, User> users = new HashMap<>();

    public UserRepository() {
        User user;
        for (int i = 1; i < 10; i++) {
            if (i == 1) {
                user = new User("admin", "admin", "admin@admin.be", "admin avenue", "0123456789", Role.ADMIN);
                System.out.println("Admin id: " + user.getId());
            } else {
                user = new User("customer" + i, "admin" + i, " customer" + i + "@customer.be", "customer avenue", "0123456789", Role.CUSTOMER);
                System.out.println("User id: " + user.getId());
            }
            users.put(user.getId(), user);
        }
    }

    public User getUserById(String id) {
        return users.values()
                .stream()
                .filter(user -> String.valueOf(user.getId()).equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User not found with Id: " + id));
    }

    public User addUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public List<User> getAllCustomers() {
        return users.values().stream()
                .filter(user -> user.getRole() == Role.CUSTOMER)
                .toList();
    }

}
