package org.example.until;

import org.example.data.manager.CustomerDataManager;
import org.example.data.manager.OrderDataManager;
import org.example.data.manager.ProductDataManager;
import org.example.repository.CustomerRepository;
import org.example.repository.ErrorRepository;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.example.service.ProductService;

public class ApplicationConfig {
    public static ProductService createProductService() {
        ProductDataManager productDataManager = new ProductDataManager();
        ErrorRepository errorRepository = new ErrorRepository();
        ProductRepository productRepository = new ProductRepository(productDataManager, errorRepository);
        return new ProductService(productRepository);
    }
    public static OrderService createOrderService() {
        OrderDataManager orderDataManager = new OrderDataManager();
        ErrorRepository errorRepository = new ErrorRepository();
        OrderRepository orderRepository = new OrderRepository();
        return new OrderService();
    }
    public static CustomerService createCustomerService() {
        CustomerDataManager  customerDataManager = new CustomerDataManager();
        ErrorRepository errorRepository = new ErrorRepository();
        CustomerRepository customerRepository = new CustomerRepository(customerDataManager, errorRepository);

        return new CustomerService(customerRepository);
    }
}
