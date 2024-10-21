package org.example.variable.common;

public enum CSVFilePath {
    //input
    PRODUCT_INPUT_PATH("src/main/java/org/example/processing_folder_path/input_folder/products.origin.csv"),
    CUSTOMER_INPUT_PATH("src/main/java/org/example/processing_folder_path/input_folder/customers.origin.csv"),
    ORDER_INPUT_PATH("src/main/java/org/example/processing_folder_path/input_folder/orders.origin.csv"),
    //output
        // model
    PRODUCT_OUTPUT_PATH("src/main/java/org/example/processing_folder_path/output_folder/products.output.csv"),
    CUSTOMER_OUTPUT_PATH("src/main/java/org/example/processing_folder_path/output_folder/customers.output.csv"),
    ORDER_OUTPUT_PATH("src/main/java/org/example/processing_folder_path/output_folder/customers.output.csv"),
        //error
    ERROR_OUTPUT_PATH("src/main/java/org/example/processing_folder_path/output_folder/error.output.csv");

    private final String filePath;

    CSVFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
