package com.ecommerce.shopping;

import com.ecommerce.shopping.cart.CartManagementService;
import com.ecommerce.shopping.offer.BuyTwoGetOffer;
import com.ecommerce.shopping.offer.OfferService;
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

    @Autowired
    private OfferService offerService;

    @Test
    void testShoppingCartTotalCostForGivenQuantity() {
        try {
            productService.addProduct(new Product(ItemName.DOVE, 39.99));
        } catch (ProductAlreadyExistsException e) {
            assertNull(e);
        }
        try {
            cartManagementService.addItem(ItemName.DOVE, 5);
        } catch (ProductNotFoundException e) {
            assertNull(e);
        }
        double totalCost = ArithmeticRounding.round(cartManagementService.getProductsCost(), 2);
        assertEquals(199.95, totalCost, "Total cost of 5 Dove Soaps each with a unit price of 39.99");
    }

    @Test
    void testShoppingCartTotalCostWhenAProductIsAddedTwice() {
        try {
            productService.addProduct(new Product(ItemName.DOVE, 39.99));
        } catch (ProductAlreadyExistsException e) {
            assertNull(e);
        }
        try {
            cartManagementService.addItem(ItemName.DOVE, 5);
            cartManagementService.addItem(ItemName.DOVE, 3);
        } catch (ProductNotFoundException e) {
            assertNull(e);
        }
        double totalCost = ArithmeticRounding.round(cartManagementService.getProductsCost(), 2);
        assertEquals(319.92, totalCost, "Total cost of 8 Dove Soaps each with a unit price of 39.99");
    }

    @Test
    void testShoppingCartTotalCostAndTotalSalesTaxWhenMultipleTypesOfItemsAreAdded() {
        try {
            productService.setSalesTaxRate(12.5);
            productService.addProduct(new Product(ItemName.DOVE, 39.99));
            productService.addProduct(new Product(ItemName.AXE_DEO, 99.99));
        } catch (ProductAlreadyExistsException e) {
            assertNull(e);
        }
        try {
            cartManagementService.addItem(ItemName.DOVE, 2);
            cartManagementService.addItem(ItemName.AXE_DEO, 2);
        } catch (ProductNotFoundException e) {
            assertNull(e);
        }

        double totalSalesTax = ArithmeticRounding.round(cartManagementService.getTotalSalesTaxAmount(), 2);
        assertEquals(35, totalSalesTax, "Total sales tax");

        double totalCost = ArithmeticRounding.round(cartManagementService.getTotalCost(), 2);
        assertEquals(totalSalesTax, 314.96, totalCost, "Total cost of 2 Dove Soaps and 2 Axe Deo including sales tax");
    }

    @Test
    void testShoppingCartTotalCostWhenProductsHaveBuy2Get1FreeOffer() {
            productService.setSalesTaxRate(12.5);
            productService.addProduct(new Product(ItemName.DOVE, 39.99));
            offerService.setOffer(new Product(ItemName.DOVE, 39.99), new BuyTwoGetOffer());

            cartManagementService.addItem(ItemName.DOVE, 3);

        double totalSalesTax = ArithmeticRounding.round(cartManagementService.getTotalSalesTaxAmount(), 2);
        assertEquals(10, totalSalesTax, "Total sales tax");

        double totalCost = ArithmeticRounding.round(cartManagementService.getTotalCost(), 2);
        assertEquals(totalSalesTax, 89.98, totalCost, "Total cost of 3 Dove Soaps with buy 2 get 1 free offer including sales tax");
    }
}
