package org.example.variable.common;

public class CSVColumn {

    public enum ProductColumn {
        ID(0, "Product Id"),
        NAME(1, "Product Name"),
        PRICE(2, "Product Price"),
        STOCK_AVAILABLE(3, "Product Stock Available");

        private final int index;
        private final String description;

        ProductColumn(int index, String description) {
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

    public enum CustomerColumn {
        ID(0, "Customer Id"),
        NAME(1, "Customer Name"),
        EMAIL(2, "Customer Email"),
        PHONE(3, "Customer Phone");

        private final int index;
        private final String description;

        CustomerColumn(int index, String description) {
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

    public enum OrderColumn {
        ID(0, "Order Id"),
        CUSTOMER_ID(1, "Customer Id"),
        PRODUCT_QUANTITIES(2, "Product Quantities"), // Cột chứa thông tin sản phẩm và số lượng
        ORDER_DATE(3, "Order Date"),
        TOTAL_PRICE(4, "Total Price");

        private final int index;
        private final String description;

        OrderColumn(int index, String description) {
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
}
