package com.kushal.fullstack;

import com.kushal.fullstack.customer.Customer;
import com.kushal.fullstack.customer.CustomerRepository;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
            Customer kushal = new Customer("Kushal", "kushalshr@gmail.com",21);

            Customer anil = new Customer("Anil", "anil@gmail.com", 19);

            List<Customer> customers = List.of(kushal, anil);
//            customerRepository.saveAll(customers);
        };
    }

}
