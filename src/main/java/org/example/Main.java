package org.example;



import org.example.data.manager.ProductDataManager;


import org.example.service.ProductService;
import org.example.variable.common.CSVFilePath;


import java.io.File;
import java.io.IOException;


public class Main {
    public ProductDataManager productDataManage;
    public static void main(String[] args) throws IOException {
            Main main = new Main();
            main.run(args);

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
