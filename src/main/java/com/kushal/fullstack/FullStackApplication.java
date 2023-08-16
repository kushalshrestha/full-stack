package com.kushal.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class FullStackApplication {

    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer kushal = new Customer(1, "Kushal", "kushalshr@gmail.com", 21);
        customers.add(kushal);
        Customer anil = new Customer(2, "Anil", "anil@gmail.com", 17);
        customers.add(anil);
    }

    public static void main(String[] args) {
        SpringApplication.run(FullStackApplication.class, args);
    }


    @GetMapping("api/v1/customers")
    public List<Customer> getCustomers() {
        return customers;
    }

    @GetMapping("api/v1/customers/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Integer customerId) {
        Customer customer = customers.stream()
                                     .filter(c -> c.getId()
                                                   .equals(customerId))
                                     .findFirst()
                                     .orElseThrow(() -> new IllegalArgumentException(("customer with id [%s] not " + "found").formatted(customerId)));
        return customer;
    }

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
