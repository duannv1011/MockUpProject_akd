package org.example.service;


import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import org.example.data.manager.ProductDataManager;
import org.example.model.Product;
import org.example.until.FilePaths;
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

    public void loadProducts() {
        String filePath = FilePaths.getPRODUCT_INPUT_PATH();
        try {
            productDataManager.processData(filePath, OperationMode.LOAD);
            storedData();
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }
    public void loadForAdd() {
        String filePath = FilePaths.getPRODUCT_NEW_PATH();
        try {
            productDataManager.processData(filePath, OperationMode.LOAD);
            storedData();
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }
    public void readKeyFromFIle() {
        String filePath = FilePaths.getPRODUCT_SEARCH_PATH();
        try {
            productDataManager.processData(filePath, OperationMode.READKEY);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }
    public void loadForUpdate() {
        String filePath = FilePaths.getPRODUCT_UPDATE_PATH();
        try {
            productDataManager.processData(filePath, OperationMode.UPDATE);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void loadForDelete() {
        String filePath = FilePaths.getPRODUCT_DELETE_PATH();
        try {
            productDataManager.processData(filePath, OperationMode.DELETE);
        } catch (IOException | CsvException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void saveToFile() {
        String filePath = FilePaths.getPRODUCT_OUTPUT_PATH();
        try {
            storedData();
            productDataManager.saveData(filePath, getProducts());
        } catch (IOException e) {
            ErrorService.logError(filePath, e.getMessage());
        }
    }

    public void storedData() {
        if (!products.isEmpty()) {
            products.clear();
        }
        products.addAll(productDataManager.getData());
    }
    public List<Product> getData() {
        return products;
    }
}
