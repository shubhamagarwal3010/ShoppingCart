package com.ecommerce.shopping;

import com.ecommerce.shopping.cart.CartManagementService;
import com.ecommerce.shopping.store.*;
import com.ecommerce.shopping.utils.ArithmeticRounding;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ShoppingCartApplicationTests {

    @Autowired
    private IProductService productService;

    @Autowired
    private CartManagementService cartManagementService;

    @Test
    void testShoppingCartTotalCostForGivenQuantity() {
        try {
            productService.addProduct(new Product(ItemName.DOVE, 39.99));
        } catch (ProductAlreadyExists e) {
            assertNull(e);
        }
        try {
            cartManagementService.addItem(ItemName.DOVE, 5);
        } catch (ProductNotFoundException e) {
            assertNull(e);
        }
        double totalCost = ArithmeticRounding.round(cartManagementService.getTotalCost(), 2);
        assertEquals(totalCost, 199.95, "Total cost of 5 Dove Soaps each with a unit price of 39.99");
    }
}
