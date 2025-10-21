package com.github.nikoilijoski.service;

import com.github.nikoilijoski.model.Order;
import com.github.nikoilijoski.model.Product;
import com.github.nikoilijoski.repo.OrderRepo;
import com.github.nikoilijoski.repo.ProductRepo;

import java.time.LocalDateTime;

public class ShopService {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    /**
     * Places a new order if the product exists.
     *
     * @param orderId      unique ID of the order
     * @param productEAN   EAN of the product to order
     * @param amount       quantity ordered
     * @param customerName name of the customer
     */
    public void placeOrder(Integer orderId, Integer productEAN, int amount, String customerName) {
        // check if product exists
        Product product = productRepo.findProductByEAN(productEAN);
        if (product == null) {
            System.out.println("⚠️ Product with EAN " + productEAN + " does not exist!");
            return;
        }

        Order order = new Order(
                orderId,
                product,
                amount,
                customerName,
                LocalDateTime.now(),
                Order.OrderStatus.PENDING
        );

        orderRepo.addOrder(order);
        System.out.println("Order placed successfully: \n" + order);
    }
}
