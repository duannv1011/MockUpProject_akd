package org.example.validator;

import java.util.List;

public interface Validator<T> {
    ValidationError validate(T item,String line);
}
