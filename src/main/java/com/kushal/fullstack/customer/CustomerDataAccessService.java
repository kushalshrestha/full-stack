package com.kushal.fullstack.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDataAccessService implements CustomerDao {

    // db
    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();

        Customer alex = new Customer(1, "Kushal", "kushalshr@gmail.com", 21);
        customers.add(alex);

        Customer jamila = new Customer(2, "Anil", "anil@gmail.com", 17);
        customers.add(jamila);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                        .filter(c -> c.getId()
                                      .equals(id))
                        .findFirst();
    }
}
