package org.example.app.service.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.product.Product;
import org.example.app.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(ProductDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        productRepository.create(request);
        return productRepository.getLastEntity()
                .orElse(null);
    }

    @Override
    public List<Product> fetchAll() {
        return productRepository.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    public Product fetchById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return productRepository.fetchById(id)
                .orElse(null);
    }

    @Override
    public Product updateById(Long id, ProductDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        if (productRepository.fetchById(id).isPresent()) {
            productRepository.updateById(id, request);
        }
        return productRepository.fetchById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (productRepository.fetchById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Product getLastEntity() {
        return productRepository.getLastEntity()
                .orElse(null);
    }
}
