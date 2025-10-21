package com.github.nikoilijoski.repo;

import com.github.nikoilijoski.model.Product;

import java.util.List;

public class ProductRepo {
    private List<Product> products;

    public ProductRepo(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findProductByEAN(Integer EAN) {
        for (Product product : products) {
            if (product.EAN().equals(EAN)) {
                return product;
            }
        }

        return null;
    }

    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.productName().equals(productName)) {
                return product;
            }
        }

        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product == null) return;

        if (products.contains(product)) {
            throw new IllegalArgumentException("Product with EAN " + product.EAN() + " already exists!");
        }

        products.add(product);
    }

    public boolean removeProduct(Product product) {
        return products.remove(product);
    }

    public void removeAllProducts() {
        products.clear();
    }
}
