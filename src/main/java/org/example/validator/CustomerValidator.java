package org.example.validator;

import org.example.model.Customer;

import java.util.List;

public class CustomerValidator implements Validator<Customer>{
    @Override
    public List<ValidationError> validate(Customer item) {
        return List.of();
    }
}
