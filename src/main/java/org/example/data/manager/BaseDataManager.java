package org.example.data.manager;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import lombok.Setter;
import org.example.until.FilePathContext;
import org.example.validator.ValidationError;
import org.example.validator.Validator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class BaseDataManager<T> {


    private final Function<String[], T> mapper;
    @Setter
    private Validator<T> validator;
    @Getter
    private final List<T> data = new ArrayList<>();
    @Getter
    private final List<ValidationError> errors = new ArrayList<>();


    public BaseDataManager(Function<String[], T> mapper, Validator<T> validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    public void loadData(String filePath) throws IOException, CsvException {
        FilePathContext.getInstance().setFilePath(getPathModelName(filePath));
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
                    T item = mapper.apply(nextLine);
                    validateAndAdd(item, lineNumber);
                } catch (Exception e) {
                    errors.add(new ValidationError(getPathModelName(filePath), String.valueOf(lineNumber), new String[]{e.getMessage()}));
                }
            }
        }
    }

    public void saveData(String filePath) throws IOException {
        clearFile(filePath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
            String[] header = getHeader();
            writer.writeNext(header);
            for (T item : data) {
                String[] line = convertToStringArray(item);
                writer.writeNext(line);
            }
        }
        System.out.println("save Ok");
    }

    public void clearFile(String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateAndAdd(T item, int lineNumber) {
        ValidationError validationErrors = validator.validate(item,String.valueOf(lineNumber));
        if (validationErrors!=null) {
            errors.add(validationErrors);
        } else {
            data.add(item);
        }
    }
    protected  String getPathModelName(String filePath) {
        File file = new File(filePath);
        return file.exists() ? file.getName() : getModelName();    }
    protected abstract String getModelName();


    protected abstract String[] getHeader();

    protected abstract String[] convertToStringArray(T item);

}
