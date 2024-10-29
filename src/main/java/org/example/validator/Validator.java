package org.example.validator;

import org.example.variable.common.OperationMode;

import java.util.List;

public interface Validator<T> {
    ValidationError validateToAdd(T item, String line);
    ValidationError validateToUpdate(T item, String line);
    ValidationError validateToDelete(T item, String line);
    ValidationError validateToReplace(T item, String s);
    ValidationError validateToReadKey(String key, String line);
}
