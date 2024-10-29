package org.example.data.manager;

import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Product;
import org.example.validator.ProductValidator;
import org.example.variable.common.CSVColumn;
import org.example.variable.common.OperationMode;

import java.io.IOException;
import java.util.List;

@Setter
@Getter
public class ProductDataManager extends BaseDataManager<Product> {
    private String fieldNameToUpdate;
    public ProductDataManager() {
        super(ProductDataManager::createProduct,
                null);
    }
    private static Product createProduct(String[] data) {
            return new Product(
                    data[CSVColumn.ProductColumn.ID.getIndex()],
                    data[CSVColumn.ProductColumn.NAME.getIndex()],
                    Double.parseDouble(data[CSVColumn.ProductColumn.PRICE.getIndex()]),
                    Integer.parseInt(data[CSVColumn.ProductColumn.STOCK_AVAILABLE.getIndex()])
            );
    }

    @Override
    public List<Product> processData(String filePath, OperationMode mode) throws IOException, CsvException {
        setValidator(new ProductValidator(getData()));
        return super.processData(filePath, mode);
    }

    @Override
    protected String getUpdateFieldName() {
        if (fieldNameToUpdate != null) {
            return fieldNameToUpdate;
        }
        return "id" ;
    }

    @Override
    protected String getItemValue(Product item, String fieldName) {
        return switch (fieldName) {
            case "id" -> item.getId();
            case "name" -> item.getName();
            default -> null;
        };
    }


    @Override
    protected String getModelName() {
        return "Product";
    }

    @Override
    protected String[] getHeader() {
        return new String[]{"Id", "Name", "Price", "StockAvailable"};
    }

    @Override
    protected String[] convertToStringArray(Product item) {
        return new String[]{
                item.getId(),
                item.getName(),
                String.valueOf(item.getPrice()),
                String.valueOf(item.getStockAvailable())
        };
    }

}
