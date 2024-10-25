package org.example.data.manager;

import com.opencsv.exceptions.CsvException;
import org.example.model.Customer;
import org.example.variable.common.CSVColumn;
import org.example.validator.CustomerValidator;
import org.example.variable.common.OperationMode;

import java.io.IOException;
import java.util.List;

public class CustomerDataManager extends BaseDataManager<Customer> {
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
        return "";
    }

    @Override
    protected String getItemValue(Customer item, String fieldName) {
        return "";
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
