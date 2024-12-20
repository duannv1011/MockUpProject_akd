package org.example.data.manager;

import com.opencsv.exceptions.CsvException;
import org.example.model.Order;
import org.example.service.ErrorService;
import org.example.variable.common.CSVColumn;
import org.example.validator.OrderValidator;
import org.example.variable.common.OperationMode;
import java.io.IOException;
import java.util.*;
public class OrderDataManager extends BaseDataManager<Order> {

    public OrderDataManager( ) {
        super(OrderDataManager::mapToOrder, null);
    }
    private static Order mapToOrder(String[] data){
           try {
               Order order = new Order();
               order.setId(data[CSVColumn.OrderColumn.ID.getIndex()]);
               order.setCustomerId(data[CSVColumn.OrderColumn
                       .CUSTOMER_ID.getIndex()]);
               Map<String, Integer> quantities = getStringIntegerMap(data);
               order.setProductQuantities(quantities);
               order.setOrderDate(data[CSVColumn.OrderColumn.ORDER_DATE.getIndex()]);
               return order;
           }catch (Exception e){
               ErrorService.logErrorWithLine(getFile(),e.getMessage(),getLine());
           }
        return null;
    }
    private static Map<String, Integer> getStringIntegerMap(String[] data) throws Exception {
        String[] productQuantities = data[CSVColumn.OrderColumn.PRODUCT_QUANTITIES.getIndex()].split(";");
        Map<String, Integer> quantities = new LinkedHashMap<>();

        for (String productQuantity : productQuantities) {
            String[] parts = productQuantity.split(":");
            if (parts.length == 2) {
                String productId = parts[0];
                int quantity = Integer.parseInt(parts[1]);

                if (quantities.containsKey(productId)) {
                    throw new Exception("Duplicate Product ID found in Product Quantity: " + productId);
                }

                quantities.put(productId, quantity);
            }
        }

        return quantities;
    }

    @Override
    public List<Order> processData(String filePath, OperationMode mode) throws IOException, CsvException {
        setValidator(new OrderValidator(getData()));
        return super.processData(filePath, mode);
    }

    @Override
    protected String getUpdateFieldName() {
        return "id";
    }

    @Override
    protected String getItemValue(Order item, String fieldName) {
        return switch (fieldName) {
            case "id" -> item.getId();
            case "customerId" -> item.getCustomerId();
            case "orderDate" -> item.getOrderDate();
            default -> null;
        };
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

        };
    }

}
