package org.example.service;

import org.example.until.ErrorFileWriter;
import org.example.validator.ValidationError;

import java.io.File;


public class ErrorService {
    public static void logError(String filePath,String message) {
        ErrorFileWriter.writeErrorToFile(createErrorService(filePath,message,null));
    }

    public static String getPathModelName(String filePath) {
        File file = new File(filePath);
        return file.exists() ? file.getName() : filePath+" not found";
    }
    protected static ValidationError createErrorService(String filePath, String message, String line) {
        return new ValidationError(
                getPathModelName(filePath),
                line==null?"":"line:"+line,
                new String[]{message}
        );
    }
    public static void logErrorWithLine(String filePath,String message,String line) {
        ErrorFileWriter.writeErrorToFile(createErrorService(filePath,message,line));
    }

}
