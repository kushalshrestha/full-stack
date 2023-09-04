package com.kushal.fullstack.integration;

import com.github.javafaker.Faker;
import com.kushal.fullstack.customer.CustomerRegistrationRequest;
import com.kushal.fullstack.customer.model.Customer;
import com.kushal.fullstack.customer.model.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {

    private static final Random RANDOM = new Random();
    private static final String CUSTOMER_URI = "api/v1/customers";
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void canRegisterCustomer() {
        Faker faker = new Faker();
        String name = faker.name()
                           .fullName();
        String email = faker.internet()
                            .safeEmailAddress() + "-" + UUID.randomUUID();
        int age = RANDOM.nextInt(1, 100);
        Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;
        CustomerRegistrationRequest customerRequest = new CustomerRegistrationRequest(name, email, age, gender);

        webTestClient.post()
                     .uri(CUSTOMER_URI)
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(Mono.just(customerRequest), CustomerRegistrationRequest.class)
                     .exchange()
                     .expectStatus()
                     .isOk();

        // get all customers
        List<Customer> allCustomers = webTestClient.get()
                                                   .uri(CUSTOMER_URI)
                                                   .accept(MediaType.APPLICATION_JSON)
                                                   .exchange()
                                                   .expectStatus()
                                                   .isOk()
                                                   .expectBodyList(new ParameterizedTypeReference<Customer>() {})
                                                   .returnResult()
                                                   .getResponseBody();

        // make sure that customer is present
        Customer expectedCustomer = new Customer(name, email, age, gender);

        assertThat(allCustomers).usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                                .contains(expectedCustomer);

        int id = allCustomers.stream()
                             .filter(customer -> customer.getEmail()
                                                         .equals(email))
                             .map(Customer::getId)
                             .findFirst()
                             .orElseThrow();

        expectedCustomer.setId(id);

        // get customer by id
        webTestClient.get()
                     .uri(CUSTOMER_URI + "/{id}", id)
                     .accept(MediaType.APPLICATION_JSON)
                     .exchange()
                     .expectStatus()
                     .isOk()
                     .expectBody(new ParameterizedTypeReference<Customer>() {})
                     .isEqualTo(expectedCustomer);
    }

    //TODO: integration test for other methods
}
