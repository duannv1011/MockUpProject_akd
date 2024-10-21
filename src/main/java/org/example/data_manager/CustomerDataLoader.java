package org.example.data_manager;

import org.example.model.Customer;
import org.example.util_enum.CSVColumn;
import org.example.validator.CustomerValidator;

public class CustomerDataLoader extends BaseDataLoader<Customer> {
    public CustomerDataLoader() {
        super(data -> new Customer(
                        data[CSVColumn.COLUMN_1.getIndex()],
                        data[CSVColumn.COLUMN_2.getIndex()],
                        data[CSVColumn.COLUMN_3.getIndex()],
                        data[CSVColumn.COLUMN_4.getIndex()]),
                new CustomerValidator());
    }
}
