package com.ecommerce.shopping.store;

import com.ecommerce.shopping.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductServiceTests {

    @Autowired
    private IProductService productService;

    @Test
    void testAddProduct() {
        try {
            productService.addProduct(new Product(ItemName.DOVE, 39.99));
        } catch (ProductAlreadyExistsException e) {
            assertNull(e);
        }
        try {
            Product product = productService.getProduct(ItemName.DOVE.getItemName());
            assertEquals(new Product(ItemName.DOVE, 39.99), product);
        } catch (ProductNotFoundException e) {
            assertNull(e);
        }
    }

    @Test
    void testShouldThrowExceptionIfSameProductIsAddedTwice() {
        try {
            productService.addProduct(new Product(ItemName.DOVE, 39.99));
            productService.addProduct(new Product(ItemName.DOVE, 99.99));
        } catch (ProductAlreadyExistsException e) {
            assertNotNull(e);
        }
    }

    @Test
    void testShouldThrowExceptionIfProductIsNotFound() {
        try {
            Product product = productService.getProduct(ItemName.DOVE.getItemName());
            assertEquals(new Product(ItemName.DOVE, 39.99), product);
        } catch (ProductNotFoundException e) {
            assertNotNull(e);
        }
    }
}
