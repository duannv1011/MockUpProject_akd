package org.example.data_manager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import org.example.validator.ValidationError;
import org.example.validator.Validator;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class BaseDataLoader<T> {


    private final Function<String[], T> mapper;
    private final Validator<T> validator;
    @Getter
    private final List<T> data = new ArrayList<>();
    @Getter
    private final List<ValidationError> errors = new ArrayList<>();


    public BaseDataLoader(Function<String[], T> mapper, Validator<T> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public void loadData(String filePath) throws IOException, CsvException {
        String fullPath = Paths.get(filePath).toString();
        try (CSVReader reader = new CSVReader(new FileReader(fullPath))) {
            String[] nextLine;
            boolean isFirstLine = true;
            int lineNumber = 0;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                lineNumber++;
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                try {
                    T item = mapper.apply(nextLine);
                    validateAndAdd(item, lineNumber);
                } catch (Exception e) {
                    String[] messages = {e.getMessage()};
                    ValidationError validationError = new ValidationError(String.valueOf(lineNumber), messages);
                    errors.add(validationError);
                }


            }
        }
    }


    private void validateAndAdd(T item, int lineNumber) {
        List<String> messages = new ArrayList<>();
        List<ValidationError> validationErrors = validator.validate(item);
        if (!validationErrors.isEmpty()) {
            for (ValidationError error : validationErrors) {
                // Thay đổi ở đây để thêm các thông báo vào danh sách messages
                messages.add(error.getMessage());
            }
            ValidationError validationError = new ValidationError(String.valueOf(lineNumber), messages.toArray(new String[0]));
            errors.add(validationError);
        } else {
            data.add(item);

        }
    }


}
