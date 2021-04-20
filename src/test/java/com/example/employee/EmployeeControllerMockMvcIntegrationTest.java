package com.example.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class EmployeeControllerMockMvcIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        repository.save(new Employee("Baek", "Myeongseok"));
    }

    @Test
    public void shouldReturnDefaultMessageWhenLastNameNotFound() throws Exception {
        String nonExistingLastName = "nonExistingLastName";
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";

        getAndAssertResponseString(nonExistingLastName, expectedMessage);
    }

    @Test
    public void shouldReturnGreetingMessageWhenLastNameFound() throws Exception {
        String existingLastName = "Baek";
        String expectedMessage = "Hello Myeongseok Baek!";

        getAndAssertResponseString(existingLastName, expectedMessage);
    }

    private void getAndAssertResponseString(String existingLastName, String expectedMessage) throws Exception {
        mockMvc.perform(get("/api/hello/")
                .param("lastName", existingLastName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value(expectedMessage))
                .andReturn();
    }
}