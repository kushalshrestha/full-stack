package com.kushal.fullstack.customer;

import com.kushal.fullstack.customer.model.Customer;
import com.kushal.fullstack.customer.model.Gender;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getInt("age"),
                Gender.valueOf(rs.getString("gender"))

        );
    }
}
