package org.example.app.repository.customer;

import org.example.app.dto.customer.CustomerDtoRequest;
import org.example.app.entity.customer.Customer;
import org.example.app.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer, CustomerDtoRequest> {
    boolean create(CustomerDtoRequest request);
    Optional<List<Customer>> fetchAll();
    Optional<Customer> fetchById(Long id);
    boolean updateById(Long id, CustomerDtoRequest request);
    boolean deleteById(Long id);
    Optional<Customer> getLastEntity();

    // ---- Query Params ----------------------
    Optional<List<Customer>> fetchByFirstName(String firstName);
    Optional<List<Customer>> fetchByLastName(String lastName);
    Optional<List<Customer>> fetchAllOrderBy(String orderBy);
}
