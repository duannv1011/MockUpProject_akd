package org.example.validator;

import lombok.Getter;
import lombok.Setter;
import org.example.until.FilePathContext;
import org.example.variable.common.OperationMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class BaseValidator<T> implements Validator<T> {
    protected final List<T> existingItems;

    public BaseValidator(List<T> existingItems) {
        this.existingItems = existingItems;
    }
    protected String getFilePath() {
        return FilePathContext.getInstance().getFilePath();
    }
    protected List<String> validateId(String value, String fieldName, boolean isUniqueCheck) {
        List<String> messages = new ArrayList<>();
        String emptyMessage = isEmpty(value, fieldName);
        if (emptyMessage != null) {
            messages.add(emptyMessage);
        }
        if (isUniqueCheck) {
            messages.add(isExist(value, fieldName));
        }
        return messages;
    }
    protected String validateNumeric(Number value, String fieldName) {
        if (value == null || value.doubleValue() < 0) {
            return fieldName + " must be greater than or equal to 0";
        }
        return null;
    }

    protected String isEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            return fieldName + " cannot be empty";
        }
        return null;
    }
    protected String matchingRegex(String value, Pattern pattern, String fieldName) {
        if (value == null || !pattern.matcher(value).matches()) {
            return fieldName + " is invalid format" ;
        }
        return null;
    }
    protected List<String> matchingRegex(String value, Pattern pattern, String fieldName, boolean isUniqueCheck) {
        List<String> messages = new ArrayList<>();

        if (value == null || !pattern.matcher(value).matches()) {
            messages.add( fieldName + " is invalid format");
        }
        if (isUniqueCheck) {
            messages.add(isExist(value, fieldName));
        }

        return messages;
    }
    protected String isExist(String value, String fieldName) {

        if (existingItems.stream().anyMatch(item -> getItem(item, fieldName).equals(value))) {
            return fieldName + " already exists: " + value;
        }
        return null;
    }
    protected String isNotExist(String value, String fieldName) {

        if (existingItems.stream().noneMatch(item -> getItem(item, fieldName).equals(value))) {
            return fieldName + " does not exist with value" + value;
        }
        return null;
    }
    protected String getItem(T item, String fieldName) {
        try {
            Field field = item.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(item);
            return value != null ? value.toString() : null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
