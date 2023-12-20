package com.switchfully.eurder.service;

import com.switchfully.eurder.entity.User;
import com.switchfully.eurder.dto.CreateCustomerDto;
import com.switchfully.eurder.dto.CustomerDto;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        try {
            User user = userMapper.mapCreateCustomerDtoToUser(createCustomerDto);
            User customer = userRepository.addUser(user);
            return userMapper.mapUserToCustomerDto(customer);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public List<CustomerDto> getAllCustomers() {
        return userRepository.getAllCustomers()
                .stream()
                .map(userMapper::mapUserToCustomerDto)
                .toList();
    }

    public CustomerDto getCustomerById(String id) {
        return userMapper.mapUserToCustomerDto(userRepository.getUserById(id));
    }

}
