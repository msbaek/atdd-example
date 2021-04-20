package com.example.employee;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class EmployeeControllerRestAssuredIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private EmployeeRepository repository;

    private RequestSpecification basicRequest;

    @BeforeEach
    public void setUp() {
        basicRequest = given()
                .baseUri("http://localhost")
                .port(8080)
        ;

        repository.deleteAll();
        repository.save(new Employee("Baek", "Myeongseok"));
    }

    @Test
    public void shouldReturnDefaultMessageWhenLastNameNotFound() {
        String nonExistingLastName = "nonExistingLastName";
        String expectedMessage = "Who is this " + nonExistingLastName  + " you're talking about?";

        getAndAssertResultString(nonExistingLastName, expectedMessage);
    }

    private void getAndAssertResultString(String nonExistingLastName, String expectedMessage) {
        given().spec(basicRequest).basePath("/api/hello/")
                .param("lastName", nonExistingLastName)
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .body("message", equalTo(expectedMessage));
    }

    @Test
    public void shouldReturnGreetingMessageWhenLastNameFound() {
        String existingLastName = "Baek";
        String expectedMessage = "Hello Myeongseok Baek!";

        getAndAssertResultString(existingLastName, expectedMessage);
    }
}