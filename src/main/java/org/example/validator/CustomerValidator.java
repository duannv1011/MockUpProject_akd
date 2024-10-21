package org.example.validator;

import org.example.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerValidator extends BaseValidator<Customer> {
    public CustomerValidator(List<Customer> existingCustomers) {
        super(existingCustomers);
    }
    @Override
    public List<ValidationError> validate(Customer customer) {
        List<ValidationError> errors = new ArrayList<>(validateId(customer.getId(), "Customer"));

        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            errors.add(new ValidationError("Customer", "Validation Errors", new String[]{"Customer name cannot be empty"}));
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            errors.add(new ValidationError("Customer", "Validation Errors", new String[]{"Email cannot be empty"}));
        } else if (!isValidEmail(customer.getEmail())) {
            errors.add(new ValidationError("Customer", "Validation Errors", new String[]{"Invalid email format: " + customer.getEmail()}));
        }
        if (customer.getPhoneNumber() != null && !customer.getPhoneNumber().trim().isEmpty()) {
            if (!isValidPhoneNumber(customer.getPhoneNumber())) {
                errors.add(new ValidationError("Customer", "Validation Errors", new String[]{"Invalid phone number format: " + customer.getPhoneNumber()}));
            }
        }

        return errors;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10,15}");
    }

    @Override
    protected String getId(Customer item) {
        return item.getId();
    }
}
