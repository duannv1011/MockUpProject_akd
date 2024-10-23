package org.example.repository;

import com.opencsv.CSVWriter;
import lombok.Getter;
import org.example.validator.ValidationError;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorRepository {
    private final List<ValidationError> errors = new ArrayList<>();


    public void addError(List<ValidationError> error) {
        errors.addAll(error);
    }

    public void saveErrors(String filepath) {
        try (CSVWriter writer =
                     new CSVWriter(new FileWriter(filepath),
                             CSVWriter.DEFAULT_SEPARATOR,
                             CSVWriter.NO_QUOTE_CHARACTER,
                             CSVWriter.NO_ESCAPE_CHARACTER,
                             CSVWriter.DEFAULT_LINE_END)) {
            for (ValidationError error : errors) {
                String[] record = {
                        "Error:"+error.getModel()+", Line:"+error.getLine()+", message:"+error.getMessage(),
                };
                writer.writeNext(record);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
