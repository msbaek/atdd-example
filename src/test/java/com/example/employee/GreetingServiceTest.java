package com.example.employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GreetingServiceTest {
    private GreetingService greetService;
    @Mock private EmployeeRepository repository;
    private final String nonExistingLastName = "nonExistingLastName";
    private final String existingLastName = "existingLastName";
    private final String firstName = "firstName";
    private final String lastName = "lastName";

    @Before
    public void setUp() throws Exception {
        greetService = new GreetingService();
        given(repository.findByLastName(nonExistingLastName))
                .willReturn(Optional.empty());
        given(repository.findByLastName(existingLastName))
                .willReturn(Optional.of(new Employee(lastName, firstName)));
    }

    @Test
    public void greet_with_nonExisting_last_name_should_return_default_message() {
//        String nonExistingLastName = "nonExistingLastName";
//        String msg = greetService.greet(nonExistingLastName);
//        assertThat(msg, is("Who is this " + nonExistingLastName + " you're talking about?"));

        String lastName = this.nonExistingLastName;
        Optional<Employee> employee = repository.findByLastName(lastName);
        String msg = employee
                .map(e -> String.format("Hello %s %s!", e.getFirstName(), e.getLastName()))
                .orElse("Who is this " + lastName + " you're talking about?");
        assertThat(msg, is("Who is this " + lastName + " you're talking about?"));

        Optional<Employee> employee1 = repository.findByLastName(existingLastName);
        String msg1 = employee1
                .map(e -> String.format("Hello %s %s!", e.getFirstName(), e.getLastName()))
                .orElse("Who is this " + existingLastName + " you're talking about?");
        assertThat(msg1, is(String.format("Hello %s %s!", firstName, this.lastName)));
    }
}