package org.example.data.manager;

import com.opencsv.exceptions.CsvException;
import org.example.model.Product;
import org.example.variable.common.CSVColumn;
import org.example.validator.ProductValidator;

import java.io.IOException;

public class ProductDataManager extends BaseDataManager<Product> {
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
    public void loadData(String filePath) throws IOException, CsvException {
        setValidator(new ProductValidator(getData()));
        super.loadData(filePath);
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
