package org.example.service;

import com.opencsv.exceptions.CsvException;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.example.variable.common.CSVFilePath;

import java.io.IOException;
import java.util.List;


public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> loadProducts() {
        try {
            return productRepository.loadProducts(CSVFilePath.PRODUCT_INPUT_PATH.getFilePath());
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return List.of(); //
        }
    }
    public void saveProduct( ) {
        productRepository.saveProducts(CSVFilePath.PRODUCT_OUTPUT_PATH.getFilePath());
    }

}
