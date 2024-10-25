package org.example.validator;

import org.apache.commons.lang3.ObjectUtils;
import org.example.model.Customer;
import org.example.variable.common.CSVColumn;
import org.example.variable.common.OperationMode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerValidator extends BaseValidator<Customer> {
    private final String MODEL = "Customer";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10,15}$"); // Giả định số điện thoại có 10-15 chữ số

    public CustomerValidator(List<Customer> existingCustomers) {
        super(existingCustomers);
    }


    @Override
    public ValidationError validateToAdd(Customer item, String line) {
        List<String> messages = new ArrayList<>();
        messages.addAll(validateId(item.getId(), CSVColumn.CustomerColumn.ID.getDescription(), true));
        messages.add(isEmpty(item.getName(), CSVColumn.CustomerColumn.NAME.getDescription()));
        messages.addAll(matchingRegex(item.getEmail(), EMAIL_PATTERN, CSVColumn.CustomerColumn.EMAIL.getDescription(), true));
        messages.addAll(matchingRegex(item.getPhoneNumber(), PHONE_PATTERN, CSVColumn.CustomerColumn.PHONE.getDescription(), true));
        messages.removeIf(ObjectUtils::isEmpty);
        if (!messages.isEmpty()) {
            return new ValidationError(MODEL, line, messages.toArray(new String[0]));
        }
        return null;
    }

    @Override
    public ValidationError validateToUpdate(Customer item, String line) {
        return null;
    }

    @Override
    public ValidationError validateToDelete(Customer item, String line) {
        return null;
    }
}
