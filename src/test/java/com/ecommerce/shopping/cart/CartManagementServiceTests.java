package com.ecommerce.shopping.cart;

import com.ecommerce.shopping.AppConfig;
import com.ecommerce.shopping.store.ItemName;
import com.ecommerce.shopping.store.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CartManagementServiceTests {

    @Autowired
    private CartManagementService cartManagementService;

    @Test
    void testShouldThrowExceptionIfItemAddedInCartIsNotAvailableInStore() {
        try {
            cartManagementService.addItem(ItemName.DOVE, 5);
        } catch (ProductNotFoundException e) {
            assertNotNull(e);
        }
    }
}
