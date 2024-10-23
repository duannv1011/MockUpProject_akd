package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.example.variable.common.CSVFilePath;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void loadProducts() {
        productRepository.loadProducts(CSVFilePath.PRODUCT_INPUT_PATH.getFilePath());
    }

    public void saveProduct() {
        productRepository.saveProducts(CSVFilePath.PRODUCT_OUTPUT_PATH.getFilePath());
    }
    public void addProduct() {
        productRepository.saveProducts(CSVFilePath.PRODUCT_INPUT_PATH.getFilePath());
    }

}
