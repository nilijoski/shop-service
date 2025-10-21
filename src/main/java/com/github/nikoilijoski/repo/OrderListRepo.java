package com.github.nikoilijoski.repo;

import com.github.nikoilijoski.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo {
    private List<Order> orders = new ArrayList<>();

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.ID() == orderId) {
                return order;
            }
        }

        return null;
    }

    public List<Order> getOrdersForCustomerName(String customerName) {
        List<Order> temp = new ArrayList<>();
        for (Order order : orders) {
            if (order.customerName().equalsIgnoreCase(customerName)) {
                temp.add(order);
            }
        }

        return temp;
    }

    public void addOrder(Order order) {
        if (order == null) return;

        if (orders.contains(order)) {
            throw new IllegalArgumentException("Order with ID " + order.ID() + " already exists!");
        }

        orders.add(order);
    }

    public boolean removeOrder(Order orderID) {
        return orders.remove(orderID);
    }

    public void removeAllOrders() {
        orders.clear();
    }
}
