package org.example.until;

import lombok.Getter;
import org.example.data.manager.CustomerDataManager;
import org.example.data.manager.OrderDataManager;
import org.example.data.manager.ProductDataManager;

import org.example.service.CommonService;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.example.service.ProductService;

@Getter
public class ApplicationConfig {
    private static ApplicationConfig instance;

    private final ProductDataManager productDataManager;
    private final CustomerDataManager customerDataManager;
    private final OrderDataManager orderDataManager;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ProductService productService;
    CommonService commonService;

    private ApplicationConfig() {
        this.productDataManager = new ProductDataManager();
        this.customerDataManager = new CustomerDataManager();
        this.orderDataManager = new OrderDataManager();
        this.productService = new ProductService(productDataManager);
        this.customerService = new CustomerService(customerDataManager);
        this.orderService = new OrderService(orderDataManager);
        this.commonService = new CommonService(productDataManager,orderDataManager);
    }

    public static synchronized ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

}