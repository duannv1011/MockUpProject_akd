package org.example.service;


import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import org.example.data.manager.ProductDataManager;
import org.example.model.Product;
import org.example.validator.ValidationError;
import org.example.variable.common.OperationMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    @Getter
    public final static List<Product> products = new ArrayList<>();
    private final ProductDataManager productDataManager;
    private ErrorService errorService;

    public ProductService(ProductDataManager productDataManager) {
        this.productDataManager = productDataManager;
        productDataManager.clearModel();
    }

    public void loadProducts(String filePath) {
        try {
            productDataManager.processData(filePath, OperationMode.LOAD);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void loadForUpdate(String filePath) {
        try {
            productDataManager.processData(filePath, OperationMode.UPDATE);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void loadForDelete(String filePath) {
        try {
            productDataManager.processData(filePath, OperationMode.DELETE);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void saveToFile(String filePath) {
        try {
            storedData();
            productDataManager.saveData(filePath, getProducts());
        } catch (IOException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public List<Product> getData() {
        return products;
    }

    public void storedData() {
        if (!products.isEmpty()) {
            products.clear();
        }
        products.addAll(productDataManager.getData());
    }
}
