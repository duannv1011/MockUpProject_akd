package org.example.service;

import org.example.repository.ErrorRepository;
import org.example.variable.common.CSVFilePath;
import org.example.validator.ValidationError;

import java.io.IOException;
import java.util.List;

public class ErrorService {
    private final ErrorRepository errorRepository;
    public ErrorService(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }
    public List<ValidationError> getErrors() {
      return   errorRepository.getErrors();
    }
    public void  saveErrors() {
        try {
            errorRepository.saveErrors(CSVFilePath.ERROR_OUTPUT_PATH.getFilePath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
