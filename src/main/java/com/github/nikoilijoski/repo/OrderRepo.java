package com.github.nikoilijoski.repo;

import com.github.nikoilijoski.model.Order;

import java.util.List;

public interface OrderRepo {
    List<Order> getAllOrders();

    void addOrder(Order order);

    boolean removeOrder(Order order);

    void removeAllOrders();

    List<Order> getOrdersForCustomerName(String customerName);

    Order getOrderById(int orderId);

}
