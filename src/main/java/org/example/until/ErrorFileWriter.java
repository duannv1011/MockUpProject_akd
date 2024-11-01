package org.example.until;


import com.opencsv.CSVWriter;
import org.example.Main;
import org.example.validator.ValidationError;
import org.example.variable.common.CSVFilePath;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class ErrorFileWriter {

    private static final String ERROR_FILE_PATH = FilePaths.getERROR_OUTPUT_PATH();

    public static void writeErrorToFile(ValidationError validationError) {
        String fullPath = Paths.get(ERROR_FILE_PATH).toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath, true))) { // Sử dụng BufferedWriter
            String timestamp = LocalDateTime.now().toString();
            String errorMessage = String.format("Timestamp: %s, Error: %s, Line: %s, Messages: %s",
                    timestamp,
                    validationError.getModel(),
                    validationError.getLine(),
                    validationError.getMessage());

            writer.write(errorMessage);
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Error writing to error log file: " + e.getMessage());
        }
    }
}
