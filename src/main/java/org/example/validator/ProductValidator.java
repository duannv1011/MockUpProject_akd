package org.example.validator;

import org.apache.commons.lang3.ObjectUtils;
import org.example.model.Product;
import org.example.variable.common.CSVColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductValidator extends BaseValidator<Product> {
    private final String MODEL = "Product";

    public ProductValidator(List<Product> existingProducts) {
        super(existingProducts);
    }

    @Override
    public ValidationError validateToAdd(Product item, String line) {
        List<String> messages = new ArrayList<>();
        messages.addAll(validateId(item.getId(), CSVColumn.ProductColumn.ID.getDescription(), true));
        messages.add(isEmpty(item.getName(), CSVColumn.ProductColumn.NAME.getDescription()));
        messages.add(validateNumeric(item.getPrice(), CSVColumn.ProductColumn.PRICE.getDescription()));
        messages.add(validateNumeric(item.getStockAvailable(), CSVColumn.ProductColumn.STOCK_AVAILABLE.getDescription()));
        messages = messages.stream()
                .filter(ObjectUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (!messages.isEmpty()) {
            return new ValidationError(getFilePath(), line, messages.toArray(new String[0]));
        }

        return null;
    }


    @Override
    public ValidationError validateToUpdate(Product item, String line) {
        List<String> messages = new ArrayList<>();
        messages.add(isNotExist(item.getId(), CSVColumn.ProductColumn.ID.getDescription()));
        messages.add(isEmpty(item.getName(), CSVColumn.ProductColumn.NAME.getDescription()));
        messages.add(validateNumeric(item.getPrice(), CSVColumn.ProductColumn.PRICE.getDescription()));
        messages.add(validateNumeric(item.getStockAvailable(), CSVColumn.ProductColumn.STOCK_AVAILABLE.getDescription()));
        messages = messages.stream()
                .filter(ObjectUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (!messages.isEmpty()) {
            return new ValidationError(getFilePath(), line, messages.toArray(new String[0]));
        }

        return null;
    }

    @Override
    public ValidationError validateToDelete(Product item, String line) {
        List<String> messages = new ArrayList<>();
        messages.add(isEmpty(item.getName(), CSVColumn.ProductColumn.ID.getDescription()));
        messages.add(isNotExist(item.getId(), CSVColumn.ProductColumn.ID.getDescription()));
        messages = messages.stream()
                .filter(ObjectUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (!messages.isEmpty()) {
            return new ValidationError(getFilePath(), line, messages.toArray(new String[0]));
        }

        return null;
    }

    @Override
    public ValidationError validateToReplace(Product item, String s) {
        return null;
    }

    @Override
    public ValidationError validateToReadKey(String key, String line) {
        List<String> messages = new ArrayList<>();
        messages.add(isEmpty(key, CSVColumn.ProductColumn.ID.getDescription()));
        messages.add(isNotExist(key, CSVColumn.ProductColumn.ID.getDescription()));
        messages.removeIf(ObjectUtils::isEmpty);
        if (!messages.isEmpty()) {
            return new ValidationError(getFilePath(), line, messages.toArray(new String[0]));
        }
        return null;
    }
}
