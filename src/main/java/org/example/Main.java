package org.example;

import org.example.service.ErrorService;
import org.example.until.ApplicationConfig;
import org.example.until.FilePaths;
import org.example.variable.common.CSVFilePath;

import java.io.File;
import java.io.IOException;

public class Main {
    private static final ApplicationConfig app = ApplicationConfig.getInstance();
    public static String errorFilePath;

    public static void main(String[] args) {
//        if (args.length < 2) {
//            System.out.println("Usage: java -jar app.jar <functionCode> <folderPath>");
//            return;
//        }
//        String functionCode = args[0];
//        String folderPath = args[1];
        //hello
        run("5.1", "C:\\Users\\Admin\\IdeaProjects\\MockUpProject\\processing_folder_path");
    }

    public static void run(String functionCode, String folderPath) {
        fileHandle(folderPath);
        switch (functionCode) {
            case "1":
                loadData();
                break;
            case "2.1":
                addProduct();
                break;
            case "2.2":
                updateProduct();
                break;
            case "2.3":
                deleteProduct();
                break;
            case "3.1":
                deleteCustomer();
                break;
            case "3.2":
                addCustomer();
                break;
            case "3.3":
                updateCustomer();
                break;
            case "4.1":
                addOrder();
                break;
            case "4.2":
                updateOrder();
                break;
            case "4.3":
                deleteOrder();
                break;
            case "5.1":
                searchTopProduct();
                break;
            case "5.2":
                searchOrderByProduct();
                break;
            default:
                ErrorService.logError(folderPath, "invalid function code");
        }
    }


    private static String getInputPath(String folderPath) {
        return folderPath + "/InputFolder";
    }

    private static String getOutputPath(String folderPath) {
        return folderPath + "/OutputFolder";
    }

    public static boolean isValidDirectory(String path) {
        File dir = new File(path);
        return dir.exists() && dir.isDirectory();
    }

    public static void fileHandle(String folderPath) {
        if (!isValidDirectory(folderPath)) {
            String projectRoot = System.getProperty("user.dir");
            File dir = new File(projectRoot, "processing_folder_path");
            System.out.println("Invalid folder path: " + folderPath);
            System.out.println("Using default directory: " + dir.getAbsolutePath());
            System.exit(1);
        }
        String inputPath = getInputPath(folderPath);
        String outputPath = getOutputPath(folderPath);
        FilePaths.setInputPaths(inputPath);
        FilePaths.setOutputPaths(outputPath);
    }

    private static void loadData() {
        loadDataFromFile();
        app.getProductService().saveToFile();
        app.getCustomerService().saveToFile();
        app.getOrderService().saveToFile();
    }

    private static void loadDataFromFile() {
        app.getProductService().loadProducts();
        app.getCustomerService().loadCustomers();
        app.getOrderService().loadOrders();
    }

    //Product
    private static void addProduct() {
        loadDataFromFile();
        app.getProductService().loadForAdd();
        app.getProductService().saveToFile();
    }

    private static void updateProduct() {
        loadDataFromFile();
        app.getProductService().loadForUpdate();
        app.getProductService().saveToFile();
    }

    private static void deleteProduct() {
        loadDataFromFile();
        app.getProductService().loadForDelete();
        app.getProductService().saveToFile();
    }

    //Customer
    private static void addCustomer() {
        loadDataFromFile();
        app.getCustomerService().loadForAdd();
        app.getCustomerService().saveToFile();
    }

    private static void updateCustomer() {
        loadDataFromFile();
        app.getCustomerService().loadForUpdate();
        app.getCustomerService().saveToFile();
    }

    private static void deleteCustomer() {
        loadDataFromFile();
        app.getCustomerService().loadForDelete();
        app.getCustomerService().saveToFile();
    }

    //Order
    private static void addOrder() {
        loadDataFromFile();
        app.getOrderService().loadForAdd();
        app.getOrderService().saveToFile();
    }

    private static void updateOrder() {
        loadDataFromFile();
        app.getOrderService().loadForUpdate();
        app.getOrderService().saveToFile();

    }

    private static void deleteOrder() {
        loadDataFromFile();
        app.getOrderService().loadForDelete();
        app.getOrderService().saveToFile();
    }

    //common
    private static void searchTopProduct() {
        loadDataFromFile();
        app.getCommonService().getAndSaveTop3Products();
    }

    private static void searchOrderByProduct() {
        loadDataFromFile();
        app.getProductService().readKeyFromFIle();
        app.getCommonService().getAndSaveOrderedProducts();
    }
}
