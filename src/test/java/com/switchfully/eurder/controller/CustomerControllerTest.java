package com.switchfully.eurder.controller;

import com.switchfully.eurder.dto.CreateCustomerDto;
import com.switchfully.eurder.dto.CustomerDto;
import com.switchfully.eurder.exception.AuthorizationException;
import com.switchfully.eurder.service.AuthorizationService;
import com.switchfully.eurder.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerControllerTest {

    private static AuthorizationService authorizationService;
    private static UserService userService;
    private static CustomerController customerController;

    private static CreateCustomerDto createCustomerDto;
    private static CustomerDto customerDto;
    private static UUID customerId;
    private static String auth;


    @BeforeAll
    static void setUp() {
        authorizationService = mock(AuthorizationService.class);
        userService = mock((UserService.class));
        customerController = new CustomerController(authorizationService, userService);
        customerId = UUID.fromString("c6a00db1-2531-4dcb-88c3-24690ef1b0e2");
        createCustomerDto = new CreateCustomerDto("firstname", "lastname", "email@address.be", "address", "123456789");
        customerDto = new CustomerDto(customerId, "firstname", "lastname", "email@address.be", "address", "123456789");
        auth = "base " + Base64.getEncoder().encodeToString("customer:password".getBytes());
    }

    @Test
    void createCustomer_ReturnsCustomerDto() {
        // Arrange
        when(userService.createCustomer(createCustomerDto)).thenReturn(customerDto);

        // Act
        CustomerDto result = customerController.createCustomer(createCustomerDto);

        // Assert
        assertNotNull(result);
        assertEquals(result, customerDto);
    }

    @Test
    void getCustomer_WhenAdmin_ReturnsCustomerDto() {
        // Arrange
        when(authorizationService.isAdmin(auth)).thenReturn(true);
        when(userService.getCustomerById(anyString())).thenReturn(customerDto);

        // Act
        CustomerDto result = customerController.getCustomer(auth, anyString());

        // Assert
        assertNotNull(result);
        assertEquals(result, customerDto);
    }

    @Test
    void getCustomer_WhenNotAdmin_ThrowsAuthorizationException() {
        // Arrange
        when(authorizationService.isAdmin(anyString())).thenReturn(false);

        // Act and Assert
        assertThrows(AuthorizationException.class,
                () -> {
                    customerController.getCustomer(auth, anyString());
                    verify(userService, never()).getCustomerById(anyString());
                });
    }

    @Test
    void getAllCustomers_WhenAdmin_ReturnsListOfCustomerDto() {
        // Arrange
        when(authorizationService.isAdmin(anyString())).thenReturn(true);
        when(userService.getAllCustomers()).thenReturn(Arrays.asList(
                customerDto,
                customerDto
        ));

        // Act
        List<CustomerDto> result = customerController.getAllCustomers(anyString());

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getAllCustomers_WhenNotAdmin_ThrowsAuthorizationException() {
        // Arrange
        when(authorizationService.isAdmin(anyString())).thenReturn(false);

        // Act and Assert
        assertThrows(AuthorizationException.class, () -> customerController.getAllCustomers(anyString()));
        verify(userService, never()).getAllCustomers();
    }

}
