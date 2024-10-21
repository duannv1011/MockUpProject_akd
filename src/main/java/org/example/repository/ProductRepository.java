package org.example.repository;

import com.opencsv.exceptions.CsvException;
import org.example.data.manager.ProductDataManager;
import org.example.model.Product;
import org.example.validator.ValidationError;

import java.io.IOException;
import java.util.List;

public class ProductRepository {
    private final ProductDataManager productDataLoader;
    private final ErrorRepository errorRepository;

    public ProductRepository(ProductDataManager productDataLoader, ErrorRepository errorRepository) {
        this.productDataLoader = productDataLoader;
        this.errorRepository = errorRepository;
    }

    public List<Product> loadProducts(String filePath) throws IOException, CsvException {
        productDataLoader.loadData(filePath);
        errorRepository.addError(productDataLoader.getErrors());
        return productDataLoader.getData();

    }

    public void saveProducts(String filePath)  {
        try {
            productDataLoader.saveData(filePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ValidationError> getErrors() {
        return productDataLoader.getErrors();
    }
}
