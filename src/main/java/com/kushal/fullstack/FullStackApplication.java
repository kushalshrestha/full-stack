package com.kushal.fullstack;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.kushal.fullstack.customer.model.Customer;
import com.kushal.fullstack.customer.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@RestController
public class FullStackApplication {

//    private static final List<Customer> customers;
//
//    static {
//        customers = new ArrayList<>();
//        Customer kushal = new Customer(1, "Kushal", "kushalshr@gmail.com", 21);
//        customers.add(kushal);
//        Customer anil = new Customer(2, "Anil", "anil@gmail.com", 17);
//        customers.add(anil);
//    }

    public static void main(String[] args) {
        SpringApplication.run(FullStackApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
//            Customer kushal = new Customer("Kushal", "kushalshr@gmail.com", 21);
//
//            Customer anil = new Customer("Anil", "anil@gmail.com", 19);
//
//            List<Customer> customers = List.of(kushal, anil);
//            customerRepository.saveAll(customers);


                var faker = new Faker();
                Random random = new Random();
                Name name = faker.name();
                String firstName = name.firstName();
                String lastName = name.lastName();
                Customer customer = new Customer(firstName + " " + lastName,
                                                 firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com",
                                                 random.nextInt(16, 99));
                customerRepository.save(customer);
            };
        }

    }
