package com.github.nikoilijoski;

import com.github.nikoilijoski.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void constructor_ShouldThrow_WhenEanNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(null, "Brand", "Name", "Desc"));
    }

    @Test
    void equals_ShouldUseOnlyEan() {
        Product p1 = new Product(123, "A", "X", "D1");
        Product p2 = new Product(123, "B", "Y", "D2");

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void equals_ShouldBeReflexiveSymmetricTransitive() {
        Product a = new Product(111, "B", "N", "D");
        Product b = new Product(111, "B2", "N2", "D2");
        Product c = new Product(111, "B3", "N3", "D3");

        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(b, a);
        assertEquals(a, c);
        assertEquals(b, c);
    }

    @Test
    void toString_ShouldContainKeyFields() {
        Product p = new Product(999, "Br", "Name", "Desc");
        String s = p.toString();
        assertTrue(s.contains("999"));
        assertTrue(s.contains("Name"));
    }

    @Test
    void equals_ShouldReturnFalse_WhenEanDifferent() {
        Product p1 = new Product(111, "BrandA", "Name1", "Desc1");
        Product p2 = new Product(222, "BrandA", "Name1", "Desc1");

        assertNotEquals(p1, p2);
    }

    @Test
    void equals_ShouldReturnFalse_WhenComparedToNull() {
        Product p1 = new Product(111, "BrandA", "Name1", "Desc1");

        assertNotEquals(null, p1);
    }

}
