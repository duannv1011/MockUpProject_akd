package org.example.service;

import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import org.example.data.manager.OrderDataManager;
import org.example.model.Order;
import org.example.model.Product;
import org.example.variable.common.OperationMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    @Getter
    public final static List<Order> orders = new ArrayList<>();
    private final OrderDataManager dataManager ;

    public OrderService(OrderDataManager dataManager) {
        this.dataManager = dataManager;
        dataManager.clearModel();

    }
    public void loadOrders(String filePath) {
        try {
            dataManager.processData(filePath, OperationMode.LOAD);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }
    public void loadForUpdate(String filePath) {
        try {
            dataManager.processData(filePath, OperationMode.UPDATE);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }
    public void loadForDelete(String filePath) {
        try {
            dataManager.processData(filePath, OperationMode.DELETE);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }
    public void saveToFile(String filePath) {
        try {
            storedData();
            dataManager.saveData(filePath, getOrders());
        } catch (IOException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void storedData() {
        if (!orders.isEmpty()) {
            orders.clear();
        }
        orders.addAll(dataManager.getData());
    }
    public List<Order> getData() {
        return orders;
    }
}
