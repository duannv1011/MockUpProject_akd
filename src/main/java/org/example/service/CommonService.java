package org.example.service;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import org.example.data.manager.CustomerDataManager;
import org.example.data.manager.OrderDataManager;
import org.example.data.manager.ProductDataManager;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Product;
import org.example.until.FilePaths;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonService {
    @Getter
    List<Product> products =new ArrayList<>();
    @Getter
    List<Order> orders =new ArrayList<>();
    private final ProductDataManager productDataManager;
    private final OrderDataManager orderDataManager;

    public CommonService(ProductDataManager productDataManager, OrderDataManager orderDataManager) {
        this.productDataManager = productDataManager;
        this.orderDataManager = orderDataManager;
    }

    public void getAndSaveTop3Products() {
        String filePath = FilePaths.getPRODUCT_OUTPUT_PATH();
        if (products.isEmpty()) {
            products.clear();
        }
        List<Product> top3Products = searchTopProduct(filePath);
        products.addAll(top3Products);
        saveToFile(filePath,top3Products);
    }
    public void getAndSaveOrderedProducts() {
        String filePath = FilePaths.getORDER_OUTPUT_PATH();
        if (orders.isEmpty()) {
            orders.clear();
        }
        List<Order> orderSearch= searchOrderByProductId();
        orders.addAll(orderSearch);
        saveToFile(filePath,orderSearch);
    }
    private List<Order> searchOrderByProductId() {

            List<String> productKeys = productDataManager.getKeys();
            List<Order> orders = orderDataManager.getData();
            List<Order> matchingOrders = orders.stream()
                .filter(order -> order.getProductQuantities().keySet().stream()
                        .anyMatch(productKeys::contains))
                .toList();

        return matchingOrders;
    }
    private List<Product> searchTopProduct(String filePath) {
        try {
            List<Product> dataProducts = productDataManager.getData();
            List<Order> orders = orderDataManager.getData();

            Map<String, Integer> productOrderQuantities = orders.stream()
                    .flatMap(order -> order.getProductQuantities().entrySet().stream())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            Integer::sum
                    ));

            List<String> topProductIds = productOrderQuantities.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(3)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            List<Product> topProducts = dataProducts.stream()
                    .filter(product -> topProductIds.contains(product.getId()))
                    .collect(Collectors.toList());

            return topProducts;
        } catch (Exception e) {
            ErrorService.logError(filePath, e.getMessage());
            System.exit(1);
        }
        return List.of();
    }


    public void clearFile(String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveToFile(String filePath,List<?> data) {
        clearFile(filePath);
        try {
            if (data.isEmpty()) {
                return;
            }

            if (data.get(0) instanceof Product) {
                productDataManager.saveData(filePath, (List<Product>) data);
            } else if (data.get(0) instanceof Order) {
                orderDataManager.saveData(filePath, (List<Order>) data);
            } else {
                System.out.println("Unsupported data type.");
                return;
            }
        } catch (IOException e) {
            ErrorService.logError(filePath, e.getMessage());
            System.exit(1);
        }
    }


}
