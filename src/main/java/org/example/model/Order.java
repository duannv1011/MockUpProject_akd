package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private String id;
    private String customerId;
    private List<OrderItem> productQuantities;
    private String orderDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static
    class OrderItem {
        private String productId;
        private int quantity;

    }
}
