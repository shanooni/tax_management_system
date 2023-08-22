package io.shanoon.taxmanagement.service;

import io.shanoon.taxmanagement.model.Customer;
import io.shanoon.taxmanagement.repository.CustomerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<Customer> getAllCustomers(int pageNumber, int pageSize){
        Pageable page  = PageRequest.of(pageNumber,pageSize);
        return customerRepository.findAll(page);
    }
}
