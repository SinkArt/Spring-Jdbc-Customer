package org.example.app.repository.customer;

import org.example.app.dto.customer.CustomerDtoRequest;
import org.example.app.entity.customer.Customer;
import org.example.app.mapper.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository("CustomerDaoImpl")
public class CustomerRepositoryImpl implements CustomerRepository {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public CustomerRepositoryImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public boolean create(CustomerDtoRequest request) {
        // Використовуємо 'customer' та колонку 'phone' (як у вашому SQL)
        String sql = "INSERT INTO customers (name, phone, address) VALUES (:name, :phone, :address)";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("name", request.name())
                .addValue("phone", request.phone())
                .addValue("address", request.address());
        return template.update(sql, paramSource) > 0;
    }

    @Override
    public Optional<List<Customer>> fetchAll() {
        String sql = "SELECT * FROM customers";
        try {
            return Optional.of(template.query(sql, new CustomerRowMapper()));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Customer> fetchById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = :id LIMIT 1";
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        try {
            return Optional.ofNullable(template.queryForObject(sql, paramSource, new CustomerRowMapper()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean updateById(Long id, CustomerDtoRequest request) {
        String sql = "UPDATE customers SET name = :name, phone = :phone, address = :address WHERE id = :id";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("name", request.name())
                .addValue("phone", request.phone())
                .addValue("address", request.address())
                .addValue("id", id);
        return template.update(sql, paramSource) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM customers WHERE id = :id";
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        return template.update(sql, paramSource) > 0;
    }

    @Override
    public Optional<Customer> getLastEntity() {
        String sql = "SELECT * FROM customers ORDER BY id DESC LIMIT 1";
        try {
            return Optional.ofNullable(template.query(sql, new MapSqlParameterSource(), new CustomerRowMapper())
                    .stream().findFirst().orElse(null));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Customer>> fetchByFirstName(String name) {
        String sql = "SELECT * FROM customers WHERE name = :name";
        SqlParameterSource paramSource = new MapSqlParameterSource("name", name);
        try {
            return Optional.of(template.query(sql, paramSource, new CustomerRowMapper()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Customer>> fetchByLastName(String phone) {
        // Оскільки в таблиці 'customers' є тільки 'phone', шукаємо по ньому
        String sql = "SELECT * FROM customers WHERE phone = :phone";
        SqlParameterSource paramSource = new MapSqlParameterSource("phone", phone);
        try {
            return Optional.of(template.query(sql, paramSource, new CustomerRowMapper()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Customer>> fetchAllOrderBy(String orderBy) {
        // УВАГА: Назви стовпчиків мають бути безпечними
        String sql = "SELECT * FROM customers ORDER BY " + orderBy;
        try {
            return Optional.of(template.query(sql, new CustomerRowMapper()));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}