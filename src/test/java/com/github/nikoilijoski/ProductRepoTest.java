package com.github.nikoilijoski;

import com.github.nikoilijoski.model.Product;
import com.github.nikoilijoski.repo.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    private ProductRepo repo;
    private Product p1, p2, p3;

    @BeforeEach
    void setUp() {
        p1 = new Product(1001, "BrandA", "Alpha", "A desc");
        p2 = new Product(1002, "BrandB", "Beta", "B desc");
        p3 = new Product(1003, "BrandC", "Gamma", "C desc");
        repo = new ProductRepo(new ArrayList<>(List.of(p1, p2)));
    }

    @Test
    void getAllProducts_ShouldReturnCurrentList() {
        assertEquals(2, repo.getAllProducts().size());
        assertTrue(repo.getAllProducts().contains(p1));
        assertTrue(repo.getAllProducts().contains(p2));
    }

    @Test
    void findProductByEAN_ShouldReturnMatchOrNull() {
        assertEquals(p1, repo.findProductByEAN(1001));
        assertNull(repo.findProductByEAN(9999));
    }

    @Test
    void findProductByName_ShouldReturnMatchOrNull() {
        assertEquals(p2, repo.findProductByName("Beta"));
        assertNull(repo.findProductByName("Nope"));
    }

    @Test
    void addProduct_ShouldAdd_WhenEanNotPresent() {
        repo.addProduct(p3);
        assertEquals(3, repo.getProducts().size());
        assertEquals(p3, repo.findProductByEAN(1003));
    }

    @Test
    void addProduct_ShouldThrow_OnDuplicateEan() {
        Product duplicateEanDifferentData =
                new Product(1001, "Other", "OtherName", "OtherDesc");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> repo.addProduct(duplicateEanDifferentData));
        assertTrue(ex.getMessage().contains("1001"));
        assertEquals(2, repo.getProducts().size());
    }

    @Test
    void removeProduct_ShouldRemoveAndReturnTrue_WhenExists() {
        assertTrue(repo.removeProduct(p1));
        assertNull(repo.findProductByEAN(1001));
        assertEquals(1, repo.getProducts().size());
    }

    @Test
    void removeProduct_ShouldReturnFalse_WhenNotInRepo() {
        assertFalse(repo.removeProduct(p3));
        assertEquals(2, repo.getProducts().size());
    }

    @Test
    void removeAllProducts_ShouldClearList() {
        repo.removeAllProducts();
        assertTrue(repo.getProducts().isEmpty());
    }

    @Test
    void findProductByEAN_ShouldReturnNull_WhenNotFound() {
        assertNull(repo.findProductByEAN(9999));
    }

    @Test
    void findProductByName_ShouldReturnNull_WhenNotFound() {
        assertNull(repo.findProductByName("DoesNotExist"));
    }

    @Test
    void addProduct_ShouldIgnoreNullProduct() {
        repo.addProduct(null);
        assertEquals(2, repo.getProducts().size());
    }

    @Test
    void removeProduct_ShouldReturnFalse_WhenProductNotFound() {
        Product unknown = new Product(9999, "BrandX", "Ghost", "Desc");
        assertFalse(repo.removeProduct(unknown));
    }


}