package com.example.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EmployeeControllerTestRestTemplateIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;
    private String baseUrl = "http://localhost:8080";

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        repository.save(new Employee("Baek", "Myeongseok"));
    }

    @Test
    public void shouldReturnDefaultMessageWhenLastNameNotFound() {
        String nonExistingLastName = "nonExistingLastName";
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";

        getAndAssertResultString(nonExistingLastName, expectedMessage);
    }

    private void getAndAssertResultString(String nonExistingLastName, String expectedMessage) {
        String result = restTemplate.getForObject(baseUrl + "/api/hello/" + nonExistingLastName, String.class);
        assertThat(result).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldReturnGreetingMessageWhenLastNameFound() {
        String existingLastName = "Baek";
        String expectedMessage = "Hello Myeongseok Baek!";

        getAndAssertResultString(existingLastName, expectedMessage);
    }
}