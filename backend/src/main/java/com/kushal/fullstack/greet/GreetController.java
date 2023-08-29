package com.kushal.fullstack.greet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreetController {
    @GetMapping("/greet")
    public GreetResponse greet(
            @RequestParam(value = "name", required = false) String name
    ) {
        String greetMessage = name == null || name.isBlank() ? "Hello" : "Hello " + name;

        GreetResponse response = new GreetResponse(greetMessage, List.of("Java", "Golang", "Javascript"), new Person("Alex", 28, 30_000));

        return response;
    }

    record Person(String name, int age, double savings) {

    }

    record GreetResponse(String greet, List<String> favProgrammingLanguages, Person person) {}
}
