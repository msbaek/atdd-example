package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private AtomicLong id = new AtomicLong(1l);
    @Autowired
    private GreetingService greetingService;

    @GetMapping("/hello/")
    public GreetingDto greeting(@RequestParam(name = "lastName") String lastName) {
        String greet = greetingService.greet(lastName);
        return createResult(greet);
    }

    private GreetingDto createResult(String greet) {
        GreetingDto greetingDto = new GreetingDto();
        greetingDto.setId(id.getAndIncrement());
        greetingDto.setMessage(greet);
        return greetingDto;
    }
}
