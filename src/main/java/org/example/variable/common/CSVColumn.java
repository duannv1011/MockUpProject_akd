package org.example.variable.common;

public class CSVColumn {

    public enum ProductColumn {
        ID(0, "id"),
        NAME(1, "name"),
        PRICE(2, "price"),
        STOCK_AVAILABLE(3, "stockAvailable");

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
        ID(0, "id"),
        NAME(1, "name"),
        EMAIL(2, "email"),
        PHONE(3, "phoneNumber");

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
