package com.ecommerce.shopping;

import com.ecommerce.shopping.cart.CartManagementService;
import com.ecommerce.shopping.store.*;
import com.ecommerce.shopping.utils.ArithmeticRounding;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ShoppingCartApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        IProductService productService = ctx.getBean(IProductService.class);
        CartManagementService cartManagementService = ctx.getBean(CartManagementService.class);

        try {
            productService.addProduct(new Product(ItemName.DOVE, 39.99));
            cartManagementService.addItem(ItemName.DOVE, 5);
        } catch (ProductAlreadyExistsException | ProductNotFoundException e) {
            e.printStackTrace();
        }
        double totalCost = ArithmeticRounding.round(cartManagementService.getProductsCost(), 2);

        System.out.println("Total Cost: " + totalCost);
    }
}

