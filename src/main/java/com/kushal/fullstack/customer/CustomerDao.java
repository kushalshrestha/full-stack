package com.kushal.fullstack.customer;

import com.kushal.fullstack.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(Integer id);

    boolean existsCustomerWithEmail(String email);

    void insertCustomer(Customer customer);

    boolean existsCustomerById(Integer id);

    void deleteCustomerById(Integer id);

    void updateCustomer(Customer customer);
}
