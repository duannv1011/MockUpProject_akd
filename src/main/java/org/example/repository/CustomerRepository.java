package org.example.repository;


import com.opencsv.exceptions.CsvException;
import org.example.data.manager.CustomerDataManager;
import org.example.model.Customer;
import org.example.validator.ValidationError;

import java.io.IOException;
import java.util.List;

public class CustomerRepository {
    private final CustomerDataManager customerDataLoader;
    private final ErrorRepository errorRepository;

    public CustomerRepository(CustomerDataManager customerDataLoader, ErrorRepository errorRepository) {
        this.customerDataLoader = customerDataLoader;
        this.errorRepository = errorRepository;
    }

    public List<Customer> loadCustomers(String filePath) {
        try {
            customerDataLoader.loadData(filePath);
            errorRepository.addError(customerDataLoader.getErrors());
            return customerDataLoader.getData();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

    }

    public void savetoFile(String filePath) {
        try {
            customerDataLoader.saveData(filePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ValidationError> getErrors() {
        return customerDataLoader.getErrors();
    }
}
