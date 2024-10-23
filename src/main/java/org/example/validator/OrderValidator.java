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
    public ValidationError validate(Order item, String line) {
        return null;
    }
}
