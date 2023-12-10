package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.dto.CreateCustomerDto;
import com.switchfully.eurder.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapCreateCustomerDtoToUser(CreateCustomerDto createCustomerDto) {
        return new User(createCustomerDto.getFirstName(),
                createCustomerDto.getLastName(),
                createCustomerDto.getEmail(),
                createCustomerDto.getAddress(),
                createCustomerDto.getPhoneNumber(),
                createCustomerDto.getRole());
    }

    public CustomerDto mapUserToCustomerDto(User user) {
        return new CustomerDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAddress(),
                user.getPhoneNumber());
    }

}
