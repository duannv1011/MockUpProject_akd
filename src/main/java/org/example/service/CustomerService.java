package org.example.service;

import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import org.example.data.manager.CustomerDataManager;
import org.example.model.Customer;
import org.example.variable.common.OperationMode;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    @Getter
    public final static List<Customer> customers = new ArrayList<>();
    private final CustomerDataManager customerDataManager;

    public CustomerService(CustomerDataManager customerDataManager) {
        this.customerDataManager = customerDataManager;
        customerDataManager.clearModel();
    }

    public void loadCustomers(String filePath) {
        try {
            customerDataManager.processData(filePath, OperationMode.LOAD);
            storedData();
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }
    public void loadForAdd(String filePath) {
        try {
            customerDataManager.processData(filePath, OperationMode.REPLACE);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void loadForUpdate(String filePath) {
        try {
            customerDataManager.processData(filePath, OperationMode.UPDATE);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void loadForDelete(String filePath) {
        try {
            customerDataManager.processData(filePath, OperationMode.DELETE);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void saveToFile(String filePath) {
        try {
            storedData();
            customerDataManager.saveData(filePath, getCustomers());
        } catch (IOException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void storedData() {
        if (!customers.isEmpty()) {
            customers.clear();
        }
        customers.addAll(customerDataManager.getData());
    }

    public List<Customer> getData() {
        return customers;
    }

}