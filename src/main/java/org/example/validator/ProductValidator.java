package org.example.validator;

import org.example.Main;
import org.example.data_manager.BaseDataLoader;
import org.example.data_manager.ProductDataLoader;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductValidator implements Validator<Product> {

    @Override
    public List<ValidationError> validate(Product product) {
        List<ValidationError> errors = new ArrayList<>();

        List<String> messages = new ArrayList<>();

        if (product.getId() == null || product.getId().isEmpty()) {
            messages.add("Product ID cannot be empty");
        }

        if (product.getName() == null || product.getName().isEmpty()) {
            messages.add("Product name cannot be empty");
        }

        if (product.getPrice() < 0) {
            messages.add("Product price cannot be negative");
        }

        if (product.getStockAvailable() < 0) {
            messages.add("Stock available cannot be negative");
        }

        // Nếu có lỗi, thêm vào danh sách lỗi
        if (!messages.isEmpty()) {
            ValidationError error = new ValidationError("Validation Errors", messages.toArray(new String[0]));
            errors.add(error);
        }

        return errors;
    }

}
