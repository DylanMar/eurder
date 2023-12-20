package com.switchfully.eurder.service;

import com.switchfully.eurder.entity.Role;
import com.switchfully.eurder.entity.User;
import com.switchfully.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthorizationService {


    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String authorization) {
        String authentication = authorization.split(" ")[1];
        byte[] authenticationDecoded = Base64.getDecoder().decode(authentication);
        String authenticationAsString = new String(authenticationDecoded);
        String username = authenticationAsString.split(":")[0];

        return userRepository.getUserById(username);
    }

    public boolean isAdmin(String authorization) {
        return getUser(authorization).getRole() == Role.ADMIN;
    }

    public boolean isCustomer(String authorization) {
        return getUser(authorization).getRole() == Role.CUSTOMER;
    }

}
