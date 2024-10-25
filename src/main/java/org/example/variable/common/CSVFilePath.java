package org.example.variable.common;

public enum CSVFilePath {
    //input
    PRODUCT_INPUT_PATH("src/main/java/org/example/processing/folder/path/input/products.origin.csv"),
    PRODUCT_INPUT_EDIT_PATH("src/main/java/org/example/processing/folder/path/input/products.edit.csv"),
    PRODUCT_INPUT_DELETE_PATH("src/main/java/org/example/processing/folder/path/input/products.delete.csv"),
    PRODUCT_INPUT_NEW_PATH("src/main/java/org/example/processing/folder/path/input/products.new.csv"),
    CUSTOMER_INPUT_PATH("src/main/java/org/example/processing/folder/path/input/customers.origin.csv"),
    ORDER_INPUT_PATH("src/main/java/org/example/processing/folder/path/input/orders.origin.csv"),
    //output
        // model
    PRODUCT_OUTPUT_PATH("src/main/java/org/example/processing/folder/path/output/products.output.csv"),
    CUSTOMER_OUTPUT_PATH("src/main/java/org/example/processing/folder/path/output/customers.output.csv"),
    ORDER_OUTPUT_PATH("src/main/java/org/example/processing/folder/path/output/customers.output.csv"),
        //error
    ERROR_OUTPUT_PATH("src/main/java/org/example/processing/folder/path/output/error.output.txt");

    private final String filePath;

    CSVFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
