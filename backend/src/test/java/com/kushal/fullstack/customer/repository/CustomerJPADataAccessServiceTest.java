package com.kushal.fullstack.customer.repository;

import com.kushal.fullstack.customer.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


public class CustomerJPADataAccessServiceTest {
    private CustomerJPADataAccessService underTest;
    private AutoCloseable autoCloseable;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
//        MockitoAnnotations - class from the Mockito framework, prepares
//        the test class with mock objects that are ready to be used in your
//        test methods.
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerJPADataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void selectAllCustomers() {
        underTest.selectAllCustomers();

//        You are using the verify() method to assert
//        that certain methods of the customerRepository mock object are being
//        called during the execution of these test methods.
        verify(customerRepository).findAll();
    }


    @Test
    void selectCustomerById(){
        int id = 1;
        underTest.selectCustomerById(id);
        verify(customerRepository).findById(id);
    }

    @Test
    void insertCustomer(){
        Customer customer = new Customer(1, "Kushal", "kushalshr@gmail.com", 20);

        underTest.insertCustomer(customer);
        verify(customerRepository).save(customer);
    }

    @Test
    void existsCustomerByEmail(){
        // Given
        String email = "kushalshr@gmail.com";
        // When
        underTest.existsCustomerWithEmail(email);
        // Then
        verify(customerRepository).existsCustomerByEmail(email);
    }

    @Test
    void existsCustomerById(){
        int id = 1;

        underTest.existsCustomerById(id);

        verify(customerRepository).existsCustomerById(id);
    }

    @Test
    void deleteCustomerById(){
        int id = 1;
        underTest.deleteCustomerById(id);
        verify(customerRepository).deleteById(id);
    }

    @Test
    void updateCustomer(){
        Customer customer = new Customer(1, "Kushal", "kushalshr@gmail.com", 20);
        underTest.updateCustomer(customer);
        verify(customerRepository).save(customer);
    }

}
