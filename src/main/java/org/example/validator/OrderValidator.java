package org.example.validator;

import org.example.model.Order;
import org.example.variable.common.OperationMode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class OrderValidator implements Validator<Order> {

    public OrderValidator(List<Order> existingOrders) {
    }

    @Override
    public ValidationError validateToAdd(Order item, String line) {
        return null;
    }

    @Override
    public ValidationError validateToUpdate(Order item, String line) {
        return null;
    }

    @Override
    public ValidationError validateToDelete(Order item, String line) {
        return null;
    }
}
