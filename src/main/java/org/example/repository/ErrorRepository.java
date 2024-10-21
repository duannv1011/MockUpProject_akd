package org.example.repository;

import lombok.Getter;
import org.example.validator.ValidationError;

import java.io.BufferedWriter;
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
    public void saveErrors(String filepath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            for (ValidationError error : errors) {
                writer.write(error.toString());
                writer.newLine();
            }
        }
    }

}
