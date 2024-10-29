package org.example.data.manager;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import lombok.Setter;
import org.example.until.ErrorFileWriter;
import org.example.until.FilePathContext;
import org.example.validator.ValidationError;
import org.example.validator.Validator;
import org.example.variable.common.OperationMode;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

public abstract class BaseDataManager<T> {

    private final Function<String[], T> mapper;
    @Setter
    private Validator<T> validator;
    @Getter
    private final List<T> data = new ArrayList<>();
    @Getter
    private final List<ValidationError> errors = new ArrayList<>();
    @Getter
    @Setter
    private String file;
    @Getter
    private List<String> keys = new ArrayList<>();


    public BaseDataManager(Function<String[], T> mapper, Validator<T> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public List<T> processData(String filePath, OperationMode mode) throws IOException, CsvException {
        FilePathContext.getInstance().setFilePath(getPathModelName(filePath));
        String fullPath = Paths.get(filePath).toString();
        setFile(fullPath);
        try (CSVReader reader = new CSVReader(new FileReader(fullPath))) {
            String[] nextLine;
            boolean isFirstLine = true;
            int lineNumber = 0;

            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                try {
                    processLine(nextLine, lineNumber, filePath, mode);
                } catch (Exception e) {
                    ValidationError error = new ValidationError(getPathModelName(filePath), String.valueOf(lineNumber), new String[]{e.getMessage()});
                    errors.add(error);
                    writerError(error);
                }

            }
        } catch (Exception e) {
            ValidationError error = new ValidationError(getPathModelName(filePath), "file", new String[]{e.getMessage()});
            errors.add(error);
            writerError(error);
        }
        return data;
    }

    public List<String> readKeys(String filePath) throws IOException, CsvException {
        Set<String> uniqueKeys = new HashSet<>();
        String fullPath = Paths.get(filePath).toString();

        try (CSVReader reader = new CSVReader(new FileReader(fullPath))) {
            String[] nextLine;
            boolean isFirstLine = true;
            int lineNumber = 0;

            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                try {
                    String key = nextLine[0];
                    if (uniqueKeys.add(key)) { // Chỉ thêm key nếu chưa tồn tại trong uniqueKeys
                        continue;
                    }
                } catch (Exception e) {
                    ValidationError error = new ValidationError(getPathModelName(filePath), String.valueOf(lineNumber), new String[]{e.getMessage()});
                    errors.add(error);
                    writerError(error);
                }
            }
        } catch (Exception e) {
            ValidationError error = new ValidationError(getPathModelName(filePath), "file", new String[]{e.getMessage()});
            errors.add(error);
            writerError(error);
        }
        return new ArrayList<>(uniqueKeys);
    }


    private void processLine(String[] nextLine, int lineNumber, String filePath, OperationMode mode) {
        try {
            switch (mode) {
                case LOAD:
                    T item = mapper.apply(nextLine);
                    validateItemToAdd(item, lineNumber);
                    break;
                case UPDATE:
                    T updatedItem = mapper.apply(nextLine);
                    validateItemToUpdate(updatedItem, lineNumber);
                    break;
                case DELETE:
                    String deleteKey = nextLine[0];
                    deleteItemFromList(deleteKey, filePath, lineNumber);
                    break;
                case REPLACE:
                    T replacedItem = mapper.apply(nextLine);
                    validateItemToReplace(replacedItem, lineNumber);
                    break;
                case READKEY:
                    String value = nextLine[0];
                    validateToReadKey(value, lineNumber);
                    break;

            }

        } catch (Exception e) {
            ValidationError error = new ValidationError(getPathModelName(filePath), String.valueOf(lineNumber), new String[]{e.getMessage()});
            errors.add(error);
            writerError(error);
        }
    }

    private void validateToReadKey(String value, int lineNumber) {
        ValidationError validationErrors = validator.validateToReadKey(value, String.valueOf(lineNumber));

        if (validationErrors != null) {
            errors.add(validationErrors);
            writerError(validationErrors);
        } else {
           keys.add(value);
        }
    }

    private void validateItemToReplace(T item, int lineNumber) {
        ValidationError validationErrors = validator.validateToReplace(item, String.valueOf(lineNumber));

        if (validationErrors != null) {
            errors.add(validationErrors);
            writerError(validationErrors);
        } else {
            replaceOrAddItemInList(item, getUpdateFieldName());
        }
    }

    public void saveData(String filePath, List<T> dataSave) throws IOException {
        clearFile(filePath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            String[] header = getHeader();
            writer.writeNext(header);
            for (T item : dataSave) {
                String[] line = convertToStringArray(item);
                writer.writeNext(line);
            }
        }
        System.out.println("save Ok");
    }

    protected void writerError(ValidationError error) {
        ErrorFileWriter.writeErrorToFile(error);
    }

    public void clearFile(String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateItemToAdd(T item, int lineNumber) {
        ValidationError validationErrors = validator.validateToAdd(item, String.valueOf(lineNumber));

        if (validationErrors != null) {
            errors.add(validationErrors);
            writerError(validationErrors);
        } else {
            data.add(item);
        }
    }

    private void validateItemToUpdate(T item, int lineNumber) {
        ValidationError validationErrors = validator.validateToUpdate(item, String.valueOf(lineNumber));

        if (validationErrors != null) {
            errors.add(validationErrors);
            writerError(validationErrors);
        } else {
            updateItemInList(item, getUpdateFieldName());
        }
    }

    // private validate for each model validation, it the same update and load
    private void validateItemToDelete(T item, int lineNumber) {
        ValidationError validationErrors = validator.validateToDelete(item, String.valueOf(lineNumber));

        if (validationErrors != null) {
            errors.add(validationErrors);
            writerError(validationErrors);
        } else {
            String deleteValue = getItemValue(item, getUpdateFieldName());
            data.removeIf(ite -> getItemValue(ite, getUpdateFieldName()).equals(deleteValue));
        }
    }

    private void deleteItemFromList(String deleteKey, String filePath, int lineNumber) {
        if (!validData(deleteKey, filePath, lineNumber)) {
            return;
        }
        boolean isDeleted = data.removeIf(item -> getItemValue(item, getUpdateFieldName()).equals(deleteKey));

        if (!isDeleted) {
            ValidationError error = new ValidationError(getPathModelName(filePath), String.valueOf(lineNumber),
                    new String[]{"Item with " + getUpdateFieldName() + ":" + deleteKey + " not found for deletion."});
            errors.add(error);
            writerError(error);
        } else {
            System.out.println(deleteKey);
        }
    }


    private boolean validData(String deleteKey, String filePath, int lineNumber) {
        if (deleteKey == null || deleteKey.trim().isEmpty()) {
            ValidationError error = new ValidationError(getPathModelName(filePath), String.valueOf(lineNumber),
                    new String[]{getUpdateFieldName() + " cannot be null or empty."});
            errors.add(error);
            writerError(error);
            return false;
        }
        return true;
    }

    private void updateItemInList(T updatedItem, String fieldName) {
        String updatedValue = getItemValue(updatedItem, fieldName);

        Optional<T> existingItemOptional = data.stream()
                .filter(existingItem -> getItemValue(existingItem, fieldName).equals(updatedValue))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            int index = data.indexOf(existingItemOptional.get());
            data.set(index, updatedItem);
        }
    }

    private void replaceOrAddItemInList(T updatedItem, String fieldName) {
        String updatedValue = getItemValue(updatedItem, fieldName);

        Optional<T> existingItemOptional = data.stream()
                .filter(existingItem -> getItemValue(existingItem, fieldName).equals(updatedValue))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            int index = data.indexOf(existingItemOptional.get());
            data.set(index, updatedItem);
        } else {
            data.add(updatedItem);
        }
    }

    public void clearModel() {
        data.clear();
    }

    protected String getPathModelName(String filePath) {
        File file = new File(filePath);
        return file.exists() ? file.getName() : getModelName();
    }

    protected abstract String getUpdateFieldName();

    protected abstract String getItemValue(T item, String fieldName);

    protected abstract String getModelName();

    protected abstract String[] getHeader();

    protected abstract String[] convertToStringArray(T item);

}
