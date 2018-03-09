package com.example.employee;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GreetingServiceTest {
    private GreetingService greetService;

    @Before
    public void setUp() throws Exception {
        greetService = new GreetingService();
    }

    @Test
    public void greet_with_nonExisting_last_name_should_return_default_message() {
        String nonExistingLastName = "nonExistingLastName";
        String msg = greetService.greet(nonExistingLastName);
        assertThat(msg, is("Who is this " + nonExistingLastName + " you're talking about?"));
    }
}