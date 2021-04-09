package com.example.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GreetingService {
    private final EmployeeRepository repository;

    public String greet(String lastName) {
        Optional<Employee> employee = repository.findByLastName(lastName);
        return employee
                .map(e -> String.format("Hello %s %s!", e.getFirstName(), e.getLastName()))
                .orElse("Who is this " + lastName + " you're talking about?");
    }
}
