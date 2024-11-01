package org.example.validator;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Product;
import org.example.service.CustomerService;
import org.example.service.ProductService;
import org.example.variable.common.CSVColumn;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class OrderValidator extends BaseValidator<Order> {
    private static final DateTimeFormatter ISO_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Getter
    final static List<Product> products = ProductService.getProducts();
    @Getter
    final static List<Customer> customers = CustomerService.getCustomers();

    public OrderValidator(List<Order> existingOrder) {
        super(existingOrder);

    }

    private String isExistCustomer(String customerId) {
        boolean exists = customers.stream().anyMatch(c -> c.getId().equals(customerId));
        return exists ? null : "Customer ID: " + customerId + " does not exist in the customer list.";
    }

    private String validateProductQuantities(Map<String, Integer> productQuantities) {
        List<String> messages = new ArrayList<>();
        Set<String> uniqueProductIds = new HashSet<>();

        for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            if (!uniqueProductIds.add(productId)) {
                    messages.add("Duplicate Product ID: " + productId + " found in the product quantities.");
            }
            boolean productExists = products.stream()
                    .anyMatch(product -> product.getId().equals(productId));
            if (!productExists) {
                messages.add("Product ID: " + productId + " does not exist in the product list.");
            }
            if (quantity <= 0) {
                messages.add("Quantity for Product ID: " + productId + " must be greater than 0.");
            }
        }

        return messages.isEmpty() ? null : String.join(", ", messages);
    }


    private String validateOrderDate(String orderDate, String fieldName) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            ZonedDateTime.parse(orderDate, formatter);
        } catch (DateTimeParseException e) {
            return fieldName + ": " + orderDate + " is not a valid ISO 8601 date-time format.";
        }
        return null;
    }
    private String validateExistForUpdate(String orderId, String newCustomerId) {
        Optional<Order> existingOrder =existingItems.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst();
        if (!existingOrder.isPresent()) {
            return "Order ID: " + orderId + " does not exist.";
        }

        boolean isCustomerValid = customers.stream()
                .anyMatch(c -> c.getId().equals(newCustomerId)) ||
                newCustomerId.equals(existingOrder.get().getCustomerId());
        return isCustomerValid ? null : "Customer ID: " + newCustomerId + " does not exist in the customer list.";
    }



    @Override
    public ValidationError validateToAdd(Order item, String line) {
        List<String> messages = new ArrayList<>();
        messages.addAll(validateId(item.getId(), CSVColumn.CustomerColumn.ID.getDescription(), true));
        messages.add(isExistCustomer(item.getCustomerId()));
        messages.addAll(validateId(item.getCustomerId(), CSVColumn.CustomerColumn.ID.getDescription(), true));
        messages.add(validateOrderDate(item.getOrderDate(), CSVColumn.OrderColumn.ORDER_DATE.getDescription()));
        messages.add(validateProductQuantities(item.getProductQuantities()));
        messages = messages.stream()
                .filter(ObjectUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (!messages.isEmpty()) {
            return new ValidationError(getFilePath(), line, messages.toArray(new String[0]));
        }

        return null;
    }


    @Override
    public ValidationError validateToUpdate(Order item, String line) {
        List<String> messages = new ArrayList<>();
        messages.add(validateExistForUpdate(item.getId(), item.getCustomerId()));
        messages.add(validateOrderDate(item.getOrderDate(), CSVColumn.OrderColumn.ORDER_DATE.getDescription()));
        messages.add(validateProductQuantities(item.getProductQuantities()));

        messages = messages.stream()
                .filter(ObjectUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (!messages.isEmpty()) {
            return new ValidationError(getFilePath(), line, messages.toArray(new String[0]));
        }

        return null;
    }


    @Override
    public ValidationError validateToDelete(Order item, String line) {
        List<String> messages = new ArrayList<>();
        messages.add(isEmpty(item.getId(),CSVColumn.OrderColumn.ID.getDescription()));
        messages.add(isNotExist(item.getId(),CSVColumn.OrderColumn.ID.getDescription()));
        messages = messages.stream()
                .filter(ObjectUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (!messages.isEmpty()) {
            return new ValidationError(getFilePath(), line, messages.toArray(new String[0]));
        }

        return null;
    }

    @Override
    public ValidationError validateToReplace(Order item, String s) {
        return null;
    }

    @Override
    public ValidationError validateToReadKey(String key, String line) {
        List<String> messages = new ArrayList<>();
        messages.addAll(validateId(key, CSVColumn.OrderColumn.ID.getDescription(), true));
        messages.removeIf(ObjectUtils::isEmpty);
        if (!messages.isEmpty()) {
            return new ValidationError(getFilePath(), line, messages.toArray(new String[0]));
        }
        return null;    }
}
