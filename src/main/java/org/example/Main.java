package org.example;

import com.opencsv.exceptions.CsvException;
import org.example.data_manager.CustomerDataLoader;
import org.example.data_manager.ProductDataLoader;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.util_enum.CSVColumn;
import org.example.validator.ValidationError;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final static String PRODUCT_FILE_PARTH = "src/main/resources/products.origin.csv";
    private final static String CUSTOMER_FILE_PARTH = "src/main/resources/customers.origin.csv";
    private final static String ORDERFILE_PARTH = "src/main/resources/orders.origin.csv";
    public  static ProductDataLoader productLoader;
    public static void main(String[] args) {
        try {
            productLoader = new ProductDataLoader();
            productLoader.loadData(PRODUCT_FILE_PARTH);
            List<Product> products= productLoader.getData();
            List<ValidationError> errors =productLoader.getErrors();
            for (ValidationError error : errors) {
                System.out.println(error);
            }
            for (Product product : products) {
                System.out.println("product:" + product);
            }



        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

}
