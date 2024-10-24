package org.example.data.manager;

import com.opencsv.exceptions.CsvException;
import org.example.model.Order;
import org.example.variable.common.CSVColumn;
import org.example.validator.OrderValidator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class OrderDataManager extends BaseDataManager<Order> {

    public OrderDataManager( ) {
        super(data -> {
            Order order = new Order();
            order.setId(data[CSVColumn.OrderColumn.ID.getIndex()]);
            order.setCustomerId(data[CSVColumn.OrderColumn
                    .CUSTOMER_ID.getIndex()]);
            Map<String, Integer> quantities = getStringIntegerMap(data);
            order.setProductQuantities(quantities);

            order.setOrderDate(LocalDateTime.parse(data[CSVColumn.OrderColumn.ORDER_DATE.getIndex()],
                    DateTimeFormatter.ISO_DATE_TIME).toString());
            return order;
        }, null);
    }

    private static Map<String, Integer> getStringIntegerMap(String[] data) {
        String[] productQuantities = data[CSVColumn.OrderColumn.PRODUCT_QUANTITIES.getIndex()].split(";");
        Map<String, Integer> quantities = new HashMap<>();
        for (String productQuantity : productQuantities) {
            String[] parts = productQuantity.split(":");
            if (parts.length == 2) {
                String productId = parts[0];
                int quantity = Integer.parseInt(parts[1]);
                quantities.put(productId, quantity);
            }
        }
        return quantities;
    }

    @Override
    public void loadData(String filePath) throws IOException, CsvException {
        setValidator(new OrderValidator(getData()));
        super.loadData(filePath);
    }

    @Override
    protected String getModelName() {
        return "Order";
    }

    @Override
    protected String[] getHeader() {
        return new String[]{"id", "customerId", "productQuantities", "orderDate"};
    }

    @Override
    protected String[] convertToStringArray(Order item) {
        return new String[]{
                item.getId(),
                item.getCustomerId(),
                String.valueOf(item.getProductQuantities()),
                String.valueOf(item.getOrderDate()),
                String.valueOf(item.getTotalPrice())

        };
    }

}
