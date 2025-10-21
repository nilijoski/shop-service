package com.github.nikoilijoski;

import com.github.nikoilijoski.model.Order;
import com.github.nikoilijoski.model.Product;
import com.github.nikoilijoski.repo.OrderListRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    private Product product;
    private Order o1, o2, o3;
    private OrderListRepo repo;

    @BeforeEach
    void setUp() {
        product = new Product(100, "Brand", "Item", "Desc");

        o1 = new Order(1, product, 2, "Max Mustermann", LocalDateTime.now(), Order.OrderStatus.PENDING);
        o2 = new Order(2, product, 1, "John Doe", LocalDateTime.now(), Order.OrderStatus.SHIPPED);
        o3 = new Order(3, product, 5, "Max Mustermann", LocalDateTime.now(), Order.OrderStatus.PAID);

        repo = new OrderListRepo();
        repo.addOrder(o1);
        repo.addOrder(o2);
    }

    @Test
    void getAllOrders_ShouldReturnAll() {
        assertEquals(2, repo.getAllOrders().size());
    }

    @Test
    void getOrderById_ShouldReturnCorrectOrder() {
        assertEquals(o1, repo.getOrderById(1));
        assertNull(repo.getOrderById(99));
    }

    @Test
    void getOrdersForCustomerName_ShouldReturnMatchingAllOrders() {
        repo.addOrder(o3);

        List<Order> found = repo.getOrdersForCustomerName("max mustermann");
        assertEquals(2, found.size());
        assertTrue(found.contains(o1));
        assertTrue(found.contains(o3));
    }

    @Test
    void addOrder_ShouldAdd_WhenNotDuplicate() {
        repo.addOrder(o3);
        assertEquals(3, repo.getAllOrders().size());
        assertTrue(repo.getAllOrders().contains(o3));
    }

    @Test
    void addOrder_ShouldThrow_WhenDuplicateId() {
        Order duplicate = new Order(1, product, 9, "Someone", LocalDateTime.now(), Order.OrderStatus.PAID);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> repo.addOrder(duplicate));
        assertTrue(ex.getMessage().contains("1"));
    }

    @Test
    void addOrder_ShouldIgnoreNull() {
        repo.addOrder(null);
        assertEquals(2, repo.getAllOrders().size());
    }

    @Test
    void removeOrder_ShouldReturnTrue_WhenExists() {
        assertTrue(repo.removeOrder(o1));
        assertEquals(1, repo.getAllOrders().size());
    }

    @Test
    void removeOrder_ShouldReturnFalse_WhenNotExists() {
        assertFalse(repo.removeOrder(o3));
        assertEquals(2, repo.getAllOrders().size());
    }

    @Test
    void removeAllOrders_ShouldClearList() {
        repo.removeAllOrders();
        assertTrue(repo.getAllOrders().isEmpty());
    }
}
