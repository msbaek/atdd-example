package com.example.employee;

import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = EmployeeApplication.class
)
@TestPropertySource(
        locations = "classpath:application-integration.properties"
)
public class EmployeeControllerRestAssuredIntegrationTest {
    @Autowired
    private EmployeeRepository repository;

    private RequestSpecification basicRequest;

    @Before
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
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";

        given().spec(basicRequest).basePath("/api/hello/" + nonExistingLastName)
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .body(is(expectedMessage));
    }

    @Test
    public void shouldReturnGreetingMessageWhenLastNameFound() {
        String existingLastName = "Baek";
        String expectedMessage = "Hello Myeongseok Baek!";

        given().spec(basicRequest).basePath("/api/hello/" + existingLastName)
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .body(is(expectedMessage));
    }
}
