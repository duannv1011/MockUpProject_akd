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
    private Map<String, Integer> productQuantities = new LinkedHashMap<>();
    private String orderDate;

}
