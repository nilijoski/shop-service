package com.github.nikoilijoski.repo;

import com.github.nikoilijoski.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo {
    private final Map<Integer, Order> orders = new HashMap<>();

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public Order getOrderById(int orderId) {
        return orders.get(orderId);
    }

    public List<Order> getOrdersForCustomerName(String customerName) {
        if (customerName == null) return List.of();

        List<Order> result = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.customerName().equalsIgnoreCase(customerName)) {
                result.add(order);
            }
        }

        return result;
    }


    public void addOrder(Order order) {
        if (order == null) return;

        if (orders.containsKey(order.ID())) {
            throw new IllegalArgumentException("Order with ID " + order.ID() + " already exists!");
        }

        orders.put(order.ID(), order);
    }

    public boolean removeOrder(Order order) {
        if (order == null) return false;
        return orders.remove(order.ID()) != null;
    }

    public void removeAllOrders() {
        orders.clear();
    }
}
