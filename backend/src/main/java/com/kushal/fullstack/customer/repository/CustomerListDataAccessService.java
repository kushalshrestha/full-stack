package com.kushal.fullstack.customer.repository;

import com.kushal.fullstack.customer.model.Customer;
import com.kushal.fullstack.customer.CustomerDao;
import com.kushal.fullstack.customer.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {

    // db
    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();

        Customer alex = new Customer("Alex", "alex@gmail.com", 21, Gender.MALE);
        customers.add(alex);

        Customer jamila = new Customer("Jamila", "jamila@gmail.com", 19, Gender.MALE);
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

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customers.stream()
                        .anyMatch(c -> c.getEmail()
                                        .equals(email));
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsCustomerById(Integer id) {
        return customers.stream()
                        .anyMatch(c -> c.getId()
                                        .equals(id));
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customers.stream()
                 .filter(c -> c.getId()
                               .equals(customerId))
                 .findFirst()
                 .ifPresent(customers::remove);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }

}
