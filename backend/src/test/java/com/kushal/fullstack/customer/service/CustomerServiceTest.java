package com.kushal.fullstack.customer.service;

import com.kushal.fullstack.customer.CustomerDao;
import com.kushal.fullstack.customer.CustomerRegistrationRequest;
import com.kushal.fullstack.customer.model.Customer;
import com.kushal.fullstack.customer.model.Gender;
import com.kushal.fullstack.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerDao customerDao;
    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerDao);
    }

    @Test
    void getAllCustomers() {
        // When
        underTest.getAllCustomers();

        // Then
        verify(customerDao).selectAllCustomers();
    }

    @Test
    void getCustomer() {
        int id = 10;
        Customer customer = new Customer("Kushal", "kushalshr@gmail.com", 20, Gender.MALE);

        when(customerDao.selectCustomerById(id)).thenReturn(Optional.of(customer));

        Customer actual = underTest.getCustomer(id);

        // Then
        assertThat(actual).isEqualTo(customer);
    }

    @Test
    void getCustomerWillThrowException() {
        int id = 10;
        when(customerDao.selectCustomerById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> underTest.getCustomer(id)).isInstanceOf(ResourceNotFoundException.class)
                                                           .hasMessage("customer with id [%s] not found".formatted(id));
    }

    @Test
    void addCustomer() {
        //Given
        String email = "kushalshr@gmail.com";
        when(customerDao.existsCustomerWithEmail(email)).thenReturn(false);

        //When
        CustomerRegistrationRequest customerRequest = new CustomerRegistrationRequest("Kushal", email, 20, Gender.MALE);
        underTest.addCustomer(customerRequest);

        //Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerDao).insertCustomer(customerArgumentCaptor.capture());
        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer.getId()).isNull();
        assertThat(capturedCustomer.getName()).isEqualTo(customerRequest.name());
        assertThat(capturedCustomer.getEmail()).isEqualTo(customerRequest.email());
        assertThat(capturedCustomer.getAge()).isEqualTo(customerRequest.age());
    }

    //TODO: Remaining test cases on service class and rowmapper
}
