package org.example;



import org.example.data.manager.CustomerDataManager;
import org.example.data.manager.OrderDataManager;
import org.example.data.manager.ProductDataManager;


import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Product;
import org.example.service.CommonService;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.example.service.ProductService;
import org.example.variable.common.CSVFilePath;


import java.io.File;
import java.io.IOException;


public class Main {
    public ProductDataManager productDataManage;
    public static void main(String[] args) throws IOException {
//            Main main = new Main();
//            main.run(args);
        CustomerDataManager customerDataManager = new CustomerDataManager();
        CustomerService customerService = new CustomerService(customerDataManager);
        ProductDataManager productDataManager = new ProductDataManager();
        ProductService productService = new ProductService(productDataManager);
        OrderDataManager orderDataManager = new OrderDataManager();
        OrderService orderService =new OrderService(orderDataManager);
        CommonService commonService=new CommonService(productDataManager,orderDataManager);
        customerService.loadCustomers(CSVFilePath.CUSTOMER_INPUT_PATH.getFilePath());


        productService.loadProducts(CSVFilePath.PRODUCT_INPUT_PATH.getFilePath());


        orderService.loadOrders(CSVFilePath.ORDER_INPUT_PATH.getFilePath());
        productService.readKeyFromFIle(CSVFilePath.PRODUCT_IDS_INPUT_PATH.getFilePath());

        commonService.getAndSaveOrderedProducts(CSVFilePath.ORDER_OUTPUT_PATH.getFilePath());




    }

    private void executeFunction(String functionCode, String folderPath) {
        switch (functionCode) {
            case "1":

                break;
            case "2.1":

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

    private void addProduct(String folderPath) {
         productDataManage = new ProductDataManager();
        ProductService dataService = new ProductService(productDataManage);
        dataService.loadProducts(CSVFilePath.PRODUCT_INPUT_PATH.getFilePath());
        dataService.loadProducts(CSVFilePath.PRODUCT_INPUT_NEW_PATH.getFilePath());
        dataService.saveToFile(CSVFilePath.PRODUCT_OUTPUT_PATH.getFilePath());
    }

    private void upDateProduct(String folderPath) {
        productDataManage = new ProductDataManager();
        ProductService dataService = new ProductService(productDataManage);
        dataService.loadProducts(CSVFilePath.PRODUCT_INPUT_PATH.getFilePath());
        dataService.loadForUpdate(CSVFilePath.PRODUCT_INPUT_EDIT_PATH.getFilePath());
        dataService.saveToFile(CSVFilePath.PRODUCT_OUTPUT_PATH.getFilePath());

    }
    private void deleteProduct(String folderPath) {
        productDataManage= new ProductDataManager();
        ProductService dataService = new ProductService(productDataManage);
        dataService.loadProducts(CSVFilePath.PRODUCT_INPUT_PATH.getFilePath());
        dataService.loadForDelete(CSVFilePath.PRODUCT_INPUT_DELETE_PATH.getFilePath());
        dataService.saveToFile(CSVFilePath.PRODUCT_OUTPUT_PATH.getFilePath());

    }

}
