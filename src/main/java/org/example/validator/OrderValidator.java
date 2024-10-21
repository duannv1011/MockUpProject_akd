package org.example.validator;

import org.example.model.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class OrderValidator implements Validator<Order> {

    public OrderValidator(List<Order> existingOrders) {
    }

    @Override
    public List<ValidationError> validate(Order order) {
        List<ValidationError> errors = new ArrayList<>();
        List<String> messages = new ArrayList<>();
        if (order.getId() == null || order.getId().trim().isEmpty()) {
            messages.add("Order ID cannot be empty");
        }
        if (order.getCustomerId() == null || order.getCustomerId().trim().isEmpty()) {
            messages.add("Order ID cannot be empty");
        }
        if (order.getProductQuantities().isEmpty()) {
            messages.add("Order Quantities cannot be empty");
        } else {
            order.getProductQuantities().forEach((productId, quantity) -> {
                if (quantity <= 0) {
                    messages.add("Quantity for product " + productId + " must be greater than zero");
                }
            });
        }
        if (order.getOrderDate() == null) {
            messages.add("Order date cannot be null");
        } else {
            try {
                LocalDateTime.parse(order.getOrderDate(), DateTimeFormatter.ISO_DATE_TIME);
            } catch (DateTimeParseException e) {
                messages.add("Order date is not in the correct ISO 8601 format");
            }
        }
        if (!messages.isEmpty()) {
            ValidationError error = new ValidationError("Order", "Validation Errors", messages.toArray(new String[0]));
            errors.add(error);
        }
        return errors;
    }
}
