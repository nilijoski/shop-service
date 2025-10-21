package com.github.nikoilijoski.model;

import java.util.Objects;

public record Product(Integer EAN,
                      String brand,
                      String productName,
                      String productDescription) {
    @Override
    public String toString() {
        return "Product{" +
                "EAN=" + EAN +
                ", brand='" + brand + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }

    public Product {
        if (EAN == null) {
            throw new IllegalArgumentException("EAN cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(EAN, product.EAN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EAN());
    }

}
