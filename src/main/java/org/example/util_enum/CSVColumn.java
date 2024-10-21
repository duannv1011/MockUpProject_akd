package org.example.util_enum;

public enum CSVColumn {
    COLUMN_1(0, "1"),
    COLUMN_2(1, "2"),
    COLUMN_3(2, "3"),
    COLUMN_4(3, "4");

    private final int index;
    private final String description;

    CSVColumn(int index, String description) {
        this.index = index;
        this.description = description.trim();
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }
}
