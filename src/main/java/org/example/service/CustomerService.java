package org.example.service;

import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import org.example.data.manager.CustomerDataManager;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.variable.common.OperationMode;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    @Getter
    public final static List<Customer> customers = new ArrayList<>();
    private final CustomerDataManager customerDataManager ;

    public CustomerService(CustomerDataManager customerDataManager) {
        this.customerDataManager = customerDataManager;
        customerDataManager.clearModel();
    }
    public List<Customer> loadCustomers(String filePath) {
        try {
            return customerDataManager.processData(filePath, OperationMode.LOAD);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Customer> getData() {
        return customers;
    }
    public void storedData(List<Customer> data) {
        customers.addAll(data);
    }
}