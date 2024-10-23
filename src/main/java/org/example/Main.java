package org.example;

import org.example.data.manager.CustomerDataManager;
import org.example.data.manager.ProductDataManager;
import org.example.model.Customer;
import org.example.model.Product;
import org.example.repository.CustomerRepository;
import org.example.repository.ErrorRepository;
import org.example.repository.ProductRepository;
import org.example.service.CustomerService;
import org.example.service.ErrorService;
import org.example.service.ProductService;
import org.example.until.ApplicationConfig;
import org.example.validator.ValidationError;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ProductDataManager productDataManager = new ProductDataManager();
        ErrorRepository errorRepository = new ErrorRepository();
        ProductService productService = new ProductService(new ProductRepository(productDataManager, errorRepository));
        ErrorService errorService = new ErrorService(errorRepository);
        //load
         productService.loadProducts();
        //save
        productService.saveProduct();
        //save error
        errorService.saveErrors();

//        CustomerDataManager customerDataManager = new CustomerDataManager();
//        ErrorRepository errorRepository = new ErrorRepository();
//        CustomerService customerService = new CustomerService(new CustomerRepository(customerDataManager, errorRepository));
//        ErrorService errorService = new ErrorService(errorRepository);
//        List< Customer> customers = customerService.loadCustomers();
//
//        customerService.saveCustomer();
//
//        errorService.saveErrors();
    }

    private void executeFunction(String functionCode, String folderPath) {
        switch (functionCode) {
            case "1":
                executeFunction1(folderPath);
                break;
            case "2":
                executeFunction2(folderPath);
                break;
            default:
                System.out.println("Invalid function code: " + functionCode);
                System.exit(1);
        }
    }

    private boolean validateArguments(String[] args) {
        return args.length == 2;
    }

    public void run(String[] args) {
        if (!validateArguments(args)) {
            System.out.println("Usage: java -jar my-console-app.jar <function_code> <folder_path>");
            System.exit(1);
        }

        String functionCode = args[0];
        String folderPath = args[1];

        if (!isValidFolder(folderPath)) {
            System.out.println("Invalid folder path: " + folderPath);
            System.exit(1);
        }

        executeFunction(functionCode, folderPath);
    }

    private boolean isValidFolder(String folderPath) {
        File folder = new File(folderPath);
        return folder.exists() && folder.isDirectory();
    }

    private void executeFunction1(String folderPath) {
        // Logic cho chức năng 1
        System.out.println("Executing function 1 in folder: " + folderPath);
        // Thêm logic xử lý của chức năng 1 tại đây
    }

    private void executeFunction2(String folderPath) {
        // Logic cho chức năng 2
        System.out.println("Executing function 2 in folder: " + folderPath);
        // Thêm logic xử lý của chức năng 2 tại đây
    }
}
