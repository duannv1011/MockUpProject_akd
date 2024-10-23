package org.example.service;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.variable.common.CSVFilePath;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> loadCustomers() {
        return customerRepository.loadCustomers(CSVFilePath.CUSTOMER_INPUT_PATH.getFilePath());
    }

    public void saveCustomer() {
        customerRepository.savetoFile(CSVFilePath.CUSTOMER_OUTPUT_PATH.getFilePath());
    }
}
