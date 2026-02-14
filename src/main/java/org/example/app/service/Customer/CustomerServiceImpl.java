package org.example.app.service.Customer;

import org.example.app.dto.customer.CustomerDtoRequest;
import org.example.app.entity.customer.Customer;
import org.example.app.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer create(CustomerDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        customerRepository.create(request);
        return customerRepository.getLastEntity()
                .orElse(null);
    }

    @Override
    public List<Customer> fetchAll() {
        return customerRepository.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    public Customer fetchById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return customerRepository.fetchById(id)
                .orElse(null);
    }

    @Override
    public Customer updateById(Long id, CustomerDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        if (customerRepository.fetchById(id).isPresent()) {
            customerRepository.updateById(id, request);
        }
        return customerRepository.fetchById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (customerRepository.fetchById(id).isPresent()) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Customer getLastEntity() {
        return customerRepository.getLastEntity()
                .orElse(null);
    }

    // ---- Query Params ----------------------

    public List<Customer> fetchByFirstName(String firstName) {
        return customerRepository.fetchByFirstName(firstName)
                .orElse(Collections.emptyList());
    }

    public List<Customer> fetchByLastName(String lastName) {
        return customerRepository.fetchByLastName(lastName)
                .orElse(Collections.emptyList());
    }

    public List<Customer> fetchAllOrderBy(String orderBy) {
        return customerRepository.fetchAllOrderBy(orderBy)
                .orElse(Collections.emptyList());
    }

}
