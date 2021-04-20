package com.example.employee

import io.restassured.specification.RequestSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static io.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.equalTo

/**
 * http://code-addict.pl/spock-restassured-docs/
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class EmployeeControllerSpockRestAssuredIntegrationTest extends Specification {
    @Autowired
    private EmployeeRepository repository;

    private RequestSpecification basicRequest;

    def setup() {
        basicRequest = given()
                .baseUri("http://localhost")
                .port(8080)

        repository.deleteAll()
        repository.save(new Employee("Baek", "Myeongseok"))
    }

    def "should return default message when last name not found"() {
        expect:
        String nonExistingLastName = "nonExistingLastName";
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";

        getAndAssertResultString(nonExistingLastName, expectedMessage)
    }


    def "should return greeting message when last name found"() {
        expect:
        String existingLastName = "Baek";
        String expectedMessage = "Hello Myeongseok Baek!";

        getAndAssertResultString(existingLastName, expectedMessage)
    }

    private void getAndAssertResultString(String nonExistingLastName, String expectedMessage) {
        given().spec(basicRequest).basePath("/api/hello/")
                .param("lastName", nonExistingLastName)
                .when().get()
                .then().log().body()
                .statusCode(HttpStatus.OK.value())
                .body("message", equalTo(expectedMessage));
    }
}