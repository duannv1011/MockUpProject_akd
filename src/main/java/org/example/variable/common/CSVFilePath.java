package org.example.variable.common;

public enum CSVFilePath {
    //input
    PRODUCT_INPUT_PATH("/products.origin.csv"),
    PRODUCT_INPUT_EDIT_PATH("/products.edit.csv"),
    PRODUCT_INPUT_DELETE_PATH("/products.delete.csv"),
    PRODUCT_INPUT_NEW_PATH("/products.new.csv"),
    PRODUCT_IDS_INPUT_PATH("/productIds.search.csv"),
    CUSTOMER_INPUT_PATH("/customers.origin.csv"),
    CUSTOMER_INPUT_NEW_PATH("/customers.new.csv"),
    CUSTOMER_INPUT_EDIT_PATH("/customers.edit.csv"),
    CUSTOMER_INPUT_DELETE_PATH("/customers.delete.csv"),
    ORDER_INPUT_PATH("/orders.origin.csv"),
    ORDER_INPUT_NEW_PATH("/orders.new.csv"),
    ORDER_INPUT_EDIT_PATH("/orders.edit.csv"),
    ORDER_INPUT_DELETE_PATH("/orders.delete.csv"),

    // Output files
    PRODUCT_OUTPUT_PATH("/products.output.csv"),
    CUSTOMER_OUTPUT_PATH("/customers.output.csv"),
    ORDER_OUTPUT_PATH("/orders.output.csv"),
    ERROR_OUTPUT_PATH("/error.output.txt");

    private final String filePath;

    CSVFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
