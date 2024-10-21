package org.example.service;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.variable.common.CSVFilePath;

import java.io.IOException;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> loadAllCustomers() {
        try {
            return customerRepository.loadCustomers(CSVFilePath.CUSTOMER_INPUT_PATH.getFilePath());

        }catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

    }
    public void saveCustomer(Customer customer) {
        customerRepository.savetoFile(CSVFilePath.CUSTOMER_OUTPUT_PATH.getFilePath());
    }
}
