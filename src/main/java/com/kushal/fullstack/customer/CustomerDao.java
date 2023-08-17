package com.kushal.fullstack.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(Integer id);

    boolean exsitsPersonWithEmail(String email);
    void insertCustomer(Customer customer);
}
