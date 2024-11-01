package org.example.until;

import lombok.Getter;
import org.example.variable.common.CSVFilePath;

public class FilePaths {
    @Getter
    private static String PRODUCT_INPUT_PATH;
    @Getter
    private static String PRODUCT_NEW_PATH;
    @Getter
    private static  String PRODUCT_SEARCH_PATH;
    @Getter
    private static String PRODUCT_UPDATE_PATH;
    @Getter
    private static String PRODUCT_DELETE_PATH;
    @Getter
    private static String CUSTOMER_INPUT_PATH;
    @Getter
    private static String CUSTOMER_DELETE_PATH;
    @Getter
    private static String CUSTOMER_UPDATE_PATH;
    @Getter
    private static String CUSTOMER_NEW_PATH;
    @Getter
    private static String ORDER_INPUT_PATH;
    @Getter
    private static String ORDER_NEW_PATH;
    @Getter
    private static String ORDER_UPDATE_PATH;
    @Getter
    private static String ORDER_DELETE_PATH;
    @Getter
    private static String PRODUCT_OUTPUT_PATH;
    @Getter
    private static String CUSTOMER_OUTPUT_PATH;
    @Getter
    private static String ORDER_OUTPUT_PATH;
    @Getter
    private static String ERROR_OUTPUT_PATH;

    public static void setInputPaths(String inputFolder) {
        //product
        PRODUCT_INPUT_PATH = inputFolder + CSVFilePath.PRODUCT_INPUT_PATH.getFilePath();
        PRODUCT_NEW_PATH =inputFolder+CSVFilePath.PRODUCT_INPUT_NEW_PATH.getFilePath();
        PRODUCT_UPDATE_PATH =inputFolder+CSVFilePath.PRODUCT_INPUT_EDIT_PATH.getFilePath();
        PRODUCT_DELETE_PATH =inputFolder+CSVFilePath.PRODUCT_INPUT_DELETE_PATH.getFilePath();
        PRODUCT_SEARCH_PATH = inputFolder+CSVFilePath.PRODUCT_IDS_INPUT_PATH.getFilePath();
        //customer
        CUSTOMER_INPUT_PATH = inputFolder + CSVFilePath.CUSTOMER_INPUT_PATH.getFilePath();
        CUSTOMER_NEW_PATH = inputFolder + CSVFilePath.CUSTOMER_INPUT_NEW_PATH.getFilePath();
        CUSTOMER_UPDATE_PATH = inputFolder + CSVFilePath.CUSTOMER_INPUT_EDIT_PATH.getFilePath();
        CUSTOMER_DELETE_PATH = inputFolder + CSVFilePath.CUSTOMER_INPUT_DELETE_PATH.getFilePath();
        //order
        ORDER_INPUT_PATH = inputFolder + CSVFilePath.ORDER_INPUT_PATH.getFilePath();
        ORDER_NEW_PATH = inputFolder + CSVFilePath.ORDER_INPUT_NEW_PATH.getFilePath();
        ORDER_UPDATE_PATH = inputFolder + CSVFilePath.ORDER_INPUT_EDIT_PATH.getFilePath();
        ORDER_DELETE_PATH = inputFolder + CSVFilePath.ORDER_INPUT_DELETE_PATH.getFilePath();

    }

    public static void setOutputPaths(String outputFolder) {
        PRODUCT_OUTPUT_PATH = outputFolder + CSVFilePath.PRODUCT_OUTPUT_PATH.getFilePath();
        CUSTOMER_OUTPUT_PATH = outputFolder + CSVFilePath.CUSTOMER_OUTPUT_PATH.getFilePath();
        ORDER_OUTPUT_PATH = outputFolder + CSVFilePath.ORDER_OUTPUT_PATH.getFilePath();
        ERROR_OUTPUT_PATH = outputFolder + CSVFilePath.ERROR_OUTPUT_PATH.getFilePath();
    }
}
