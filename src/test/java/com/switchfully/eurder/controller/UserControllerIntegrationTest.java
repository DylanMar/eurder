package com.switchfully.eurder.controller;

import com.switchfully.eurder.dto.userdto.UserDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

//@TestConfiguration
//@ComponentScan(basePackages = "com.switchfully.eurder")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserControllerIntegrationTest {

    @LocalServerPort
    private int port = 8080;

    @Test
    void whenAdminLogin_whenGetAllMembers_thenGetAllMembers() {
        // GIVEN
        String email = "1";
        String password = "admin";

        //WHEN
        List<UserDto> actual =
                RestAssured
                        .given()
                        .contentType(JSON)
                        .header("email", email)
                        .header("password", password)
                        .when()
                        .port(port)
                        .get("/division")
                        .then()
                        .assertThat()
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", UserDto.class);

        //THEN
        assertThat(actual).isNotEmpty();
    }

}