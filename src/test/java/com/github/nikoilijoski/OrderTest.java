package com.github.nikoilijoski;

import com.github.nikoilijoski.model.Order;
import com.github.nikoilijoski.model.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private final Product product = new Product(111, "BrandX", "TestProduct", "Description");

    @Test
    void constructor_ShouldThrow_WhenIdNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Order(null, product, 2, "Niko", LocalDateTime.now(), Order.OrderStatus.PENDING));
    }

    @Test
    void equals_ShouldReturnTrue_WhenSameId() {
        Order o1 = new Order(1, product, 2, "Niko", LocalDateTime.now(), Order.OrderStatus.PENDING);
        Order o2 = new Order(1, product, 5, "Other", LocalDateTime.now(), Order.OrderStatus.DELIVERED);

        assertEquals(o1, o2);
        assertEquals(o1.hashCode(), o2.hashCode());
    }

    @Test
    void equals_ShouldReturnFalse_WhenDifferentId() {
        Order o1 = new Order(1, product, 2, "A", LocalDateTime.now(), Order.OrderStatus.PENDING);
        Order o2 = new Order(2, product, 2, "A", LocalDateTime.now(), Order.OrderStatus.PENDING);

        assertNotEquals(o1, o2);
    }

    @Test
    void equals_ShouldReturnFalse_WhenComparedWithNullOrDifferentType() {
        Order o1 = new Order(1, product, 2, "A", LocalDateTime.now(), Order.OrderStatus.PENDING);

        assertNotEquals(o1, null);
        assertNotEquals(o1, "string");
    }

    @Test
    void toString_ShouldContainKeyFields() {
        Order o1 = new Order(123, product, 2, "Niko", LocalDateTime.of(2025, 10, 21, 10, 0),
                Order.OrderStatus.PROCESSING);

        String s = o1.toString();
        assertTrue(s.contains("123"));
        assertTrue(s.contains("PROCESSING"));
        assertTrue(s.contains("Niko"));
    }
}
