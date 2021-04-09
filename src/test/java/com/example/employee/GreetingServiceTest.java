package com.example.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GreetingServiceTest {
    private GreetingService greetService;
    @Mock private EmployeeRepository repository;
    private final String nonExistingLastName = "nonExistingLastName";
    private final String existingLastName = "existingLastName";
    private final String firstName = "firstName";
    private final String lastName = "lastName";

    @BeforeEach
    public void setUp() throws Exception {
        greetService = new GreetingService(repository);
    }

    @Test
    public void greet_with_nonExisting_last_name_should_return_default_message() {
        given(repository.findByLastName(nonExistingLastName))
                .willReturn(Optional.empty());
        String msg = greetService.greet(nonExistingLastName);
        assertThat(msg).isEqualTo("Who is this " + nonExistingLastName + " you're talking about?");
    }

    @Test
    public void greet_with_existing_last_name_should_return_hello_message_with_appropriate_names() {
        given(repository.findByLastName(existingLastName))
                .willReturn(Optional.of(new Employee(lastName, firstName)));
        String msg1 = greetService.greet(existingLastName);
        assertThat(msg1).isEqualTo(String.format("Hello %s %s!", firstName, lastName));
    }
}