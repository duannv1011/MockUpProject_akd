package org.example.data.manager;

import com.opencsv.exceptions.CsvException;
import lombok.Setter;
import org.example.model.Customer;
import org.example.variable.common.CSVColumn;
import org.example.validator.CustomerValidator;
import org.example.variable.common.OperationMode;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Setter
public class CustomerDataManager extends BaseDataManager<Customer> {
    private String fieldNameToUpdate;
    public CustomerDataManager() {
        super(data -> new Customer(
                        data[CSVColumn.CustomerColumn.ID.getIndex()],
                        data[CSVColumn.CustomerColumn.NAME.getIndex()],
                        data[CSVColumn.CustomerColumn.EMAIL.getIndex()],
                        data[CSVColumn.CustomerColumn.PHONE.getIndex()]),
                null);
    }
    @Override
    public List<Customer> processData(String filePath, OperationMode mode) throws IOException, CsvException {
        setValidator(new CustomerValidator(getData()));
        return super.processData(filePath,mode);
    }

    @Override
    protected String getUpdateFieldName() {
        return Objects.requireNonNullElse(fieldNameToUpdate, "phoneNumber");
    }

    @Override
    protected String getItemValue(Customer item, String fieldName) {
        return switch (fieldName) {
            case "id" -> item.getId();
            case "name" -> item.getName();
            case "email" -> item.getEmail();
            case "phoneNumber" -> item.getPhoneNumber();
            default -> null;
        };
    }

    @Override
    protected String getModelName() {
        return "Customer";
    }

    @Override
    protected String[] getHeader() {
        return new String[]{"id", "name", "email", "phoneNumber"};
    }

    @Override
    protected String[] convertToStringArray(Customer item) {
        return new String[]{
                item.getId(),
                item.getName(),
                String.valueOf(item.getEmail()),
                String.valueOf(item.getPhoneNumber())
        };
    }
}
