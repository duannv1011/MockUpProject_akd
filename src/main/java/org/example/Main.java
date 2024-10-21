package org.example;

import org.example.data.manager.ProductDataManager;
import org.example.model.Product;
import org.example.repository.ErrorRepository;
import org.example.repository.ProductRepository;
import org.example.service.ErrorService;
import org.example.service.ProductService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProductDataManager productDataManager = new ProductDataManager();
        ErrorRepository errorRepository = new ErrorRepository();
        ProductService productService = new ProductService(new ProductRepository(productDataManager,errorRepository));
        ErrorService errorService = new ErrorService(errorRepository);
        List<Product> products = productService.loadProducts();

        productService.saveProduct();
        errorService.saveErrors();
    }
}
