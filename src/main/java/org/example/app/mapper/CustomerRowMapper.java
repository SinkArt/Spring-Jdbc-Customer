package org.example.app.mapper;

import org.example.app.entity.customer.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper перетворює рядок з таблиці 'users' (ResultSet)
 * у Java-об'єкт 'Customer'.
 */
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        // ВАЖЛИВО: назви в лапках мають точно збігатися з колонками в pgAdmin
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setPhone(rs.getString("phone"));
        customer.setAddress(rs.getString("address"));
        return customer;
    }
}