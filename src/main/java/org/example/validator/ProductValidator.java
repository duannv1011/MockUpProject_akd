package org.example.validator;

import org.apache.commons.lang3.ObjectUtils;
import org.example.model.Product;
import org.example.variable.common.CSVColumn;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator extends BaseValidator<Product> {
    private final String MODEL = "Product";

    public ProductValidator(List<Product> existingProducts) {
        super(existingProducts);
    }


    @Override
    public ValidationError validate(Product item, String line) {

        List<String> messages = new ArrayList<>();
        messages.addAll(validateId(item.getId(), CSVColumn.ProductColumn.ID.getDescription(), true));
        messages.add(isEmpty(item.getName(), CSVColumn.ProductColumn.NAME.getDescription()));
        messages.add(validateNumeric(item.getPrice(), CSVColumn.ProductColumn.PRICE.getDescription()));
        messages.add(validateNumeric(item.getStockAvailable(), CSVColumn.ProductColumn.STOCK_AVAILABLE.getDescription()));
        messages.removeIf(ObjectUtils::isEmpty);
        if (!messages.isEmpty()) {
            return new ValidationError(getFilePath(), line, messages.toArray(new String[0]));
        }

        return null;
    }

}
