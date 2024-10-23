package org.example.until;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilePathContext {
    private static FilePathContext instance;
    private String filePath;

    private FilePathContext() {}

    public static FilePathContext getInstance() {
        if (instance == null) {
            instance = new FilePathContext();
        }
        return instance;
    }

}
