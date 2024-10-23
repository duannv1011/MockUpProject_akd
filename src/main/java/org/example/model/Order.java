package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private String id;
    private String customerId;
    private Map<String, Integer> productQuantities = new HashMap<>();
    private String orderDate;
    private double totalPrice;

    public void calculateTotal(List<Product> products) {
        totalPrice = productQuantities.entrySet().stream()
                .mapToDouble(entry -> {
                    String productId = entry.getKey();
                    int quantity = entry.getValue();
                    return products.stream()
                            .filter(product -> product.getId().equals(productId))
                            .findFirst()
                            .map(product -> product.getPrice() * quantity)
                            .orElse(0.0);
                })
                .sum();
    }

}
