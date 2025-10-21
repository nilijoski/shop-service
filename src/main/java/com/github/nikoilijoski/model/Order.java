package com.github.nikoilijoski.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record Order(Integer ID, Product product, int amount, String customerName, LocalDateTime dateTime,
                    OrderStatus orderStatus) {
    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                ", product=" + product +
                ", amount=" + amount +
                ", customerName='" + customerName + '\'' +
                ", dateTime=" + dateTime +
                ", orderStatus=" + orderStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return Objects.equals(ID, order.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID());
    }

    public Order {
        if (ID == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
    }

    public enum OrderStatus {
        PENDING,
        PAID,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED,
        RETURNED;
    }
}

