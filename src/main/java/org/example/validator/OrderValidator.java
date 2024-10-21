package org.example.validator;

import org.example.model.Order;

import java.util.List;

public class OrderValidator implements Validator<Order> {
    @Override
    public List<ValidationError> validate(Order item) {
        return List.of();
    }
}
