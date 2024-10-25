package org.example.validator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {
    private String model;
    private String line;
    private String[] message;
    public String getMessage() {
        return String.join(", ", message);
    }
    public String getDetailedError() {

        return String.format("Error:%s, Line: %s, Messages: %s", model, line, getMessage()).replace("\"", "");
    }

}
