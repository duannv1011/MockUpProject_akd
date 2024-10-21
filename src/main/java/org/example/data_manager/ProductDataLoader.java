package org.example.data_manager;

import org.example.model.Product;
import org.example.util_enum.CSVColumn;
import org.example.validator.ProductValidator;

public class ProductDataLoader extends BaseDataLoader<Product> {
    public ProductDataLoader() {
        super(data -> new Product(
                        data[CSVColumn.COLUMN_1.getIndex()],
                        data[CSVColumn.COLUMN_2.getIndex()],
                        Double.parseDouble(data[CSVColumn.COLUMN_3.getIndex()]),
                        Integer.parseInt(data[CSVColumn.COLUMN_4.getIndex()])),
                new ProductValidator());
    }

}
