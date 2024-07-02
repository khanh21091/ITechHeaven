package org.example.itech_heaven.Service;

import org.example.itech_heaven.Entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(int id);
    List<Order> getOrdersByStatus(String status);
    long countOrders();
}
