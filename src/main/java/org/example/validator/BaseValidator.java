package org.example.validator;

import java.util.List;
import java.util.ArrayList;

public abstract class BaseValidator<T> implements Validator<T> {
    protected final List<T> existingItems;

    public BaseValidator(List<T> existingItems) {
        this.existingItems = existingItems;
    }

    protected List<ValidationError> validateId(String id, String modelName) {
        List<ValidationError> errors = new ArrayList<>();
        List<String> messages = new ArrayList<>();

        if (id == null || id.isEmpty()) {
            messages.add(modelName + " ID cannot be empty");
        }
        if (existingItems.stream().anyMatch(item -> getId(item).equals(id))) {
            messages.add(modelName + " ID already exists: " + id);
        }

        if (!messages.isEmpty()) {
            errors.add(new ValidationError(modelName, "Validation Errors", messages.toArray(new String[0])));
        }

        return errors;
    }

    protected abstract String getId(T item);
}
