package com.switchfully.eurder.repository;

import com.switchfully.eurder.entity.Role;
import com.switchfully.eurder.entity.User;
import com.switchfully.eurder.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void getUserById_ExistingId_ReturnsUser() {
        UUID userId = userRepository.getAllCustomers().get(0).getId();
        User retrievedUser = userRepository.getUserById(userId.toString());
        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getId());
    }

    @Test
    void getUserById_NonExistingId_ThrowsNotFoundException() {
        String nonExistingId = UUID.randomUUID().toString();
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> userRepository.getUserById(nonExistingId));
        assertEquals("User not found with Id: " + nonExistingId, exception.getMessage());
    }

    @Test
    void addUser_NewUser_ReturnsAddedUser() {
        User newUser = new User("NewUser", "NewPassword", "newuser@example.com", "New Avenue", "0123456789", Role.CUSTOMER);
        User addedUser = userRepository.addUser(newUser);

        assertNotNull(addedUser.getId());
        assertEquals(newUser.getFirstName(), addedUser.getFirstName());
        assertEquals(newUser.getEmail(), addedUser.getEmail());
        assertEquals(newUser.getAddress(), addedUser.getAddress());
        assertEquals(newUser.getPhoneNumber(), addedUser.getPhoneNumber());
        assertEquals(newUser.getRole(), addedUser.getRole());
    }

    @Test
    void getAllCustomers_ReturnsAllCustomersInRepository() {
        List<User> allCustomers = userRepository.getAllCustomers();
        assertNotNull(allCustomers);
        assertNotNull(allCustomers);
    }
}
