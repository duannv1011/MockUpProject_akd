package org.example.validator;

import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductValidator extends BaseValidator<Product> {

    public ProductValidator(List<Product> existingProducts) {
        super(existingProducts);
    }

    @Override
    public List<ValidationError> validate(Product product) {
        List<ValidationError> errors = new ArrayList<>();
        validateId(product, errors);

        List<String> messages = new ArrayList<>();
        validateFields(product, messages);

        if (!messages.isEmpty()) {
            ValidationError error = new ValidationError("Product", "Validation Errors", messages.toArray(new String[0]));
            errors.add(error);
        }

        return errors;
    }
    private void validateId(Product product, List<ValidationError> errors) {
        List<ValidationError> idErrors = validateId(product.getId(), "Product");
        errors.addAll(idErrors);
    }

    private void validateFields(Product product, List<String> messages) {
        if (product.getId() == null || product.getId().trim().isEmpty()) {
            messages.add("Product ID cannot be empty");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            messages.add("Product name cannot be empty");
        }
        if (product.getPrice() < 0) {
            messages.add("Product price cannot be negative");
        }
        if (product.getStockAvailable() < 0) {
            messages.add("Stock available cannot be negative");
        }
    }

    @Override
    protected String getId(Product item) {
        return item.getId();
    }
}
