package org.example.app.repository.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.product.Product;
import org.example.app.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository("ProductDaoImpl")
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean create(ProductDtoRequest request) {
        String sql = "INSERT INTO products (name, measure, quota, price) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                request.productName(),
                request.measure(),
                request.quota(),
                request.price()) > 0;
    }

    @Override
    public Optional<List<Product>> fetchAll() {
        String sql = "SELECT * FROM products";
        try {
            return Optional.of(jdbcTemplate.query(sql, new ProductRowMapper()));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> fetchById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ? LIMIT 1";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public boolean updateById(Long id, ProductDtoRequest request) {
        String sql = "UPDATE products SET name = ?, measure = ?, quota = ?, price = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                request.productName(),
                request.measure(),
                request.quota(),
                request.price(),
                id) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public Optional<Product> getLastEntity() {
        String sql = "SELECT * FROM products ORDER BY id DESC LIMIT 1";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ProductRowMapper()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}