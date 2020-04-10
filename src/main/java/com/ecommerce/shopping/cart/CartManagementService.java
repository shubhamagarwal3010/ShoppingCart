package com.ecommerce.shopping.cart;

import com.ecommerce.shopping.offer.OfferService;
import com.ecommerce.shopping.store.IProductService;
import com.ecommerce.shopping.store.ItemName;
import com.ecommerce.shopping.store.Product;
import com.ecommerce.shopping.store.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CartManagementService {

    @Autowired
    private IProductService productService;
    @Autowired
    private OfferService offerService;

    private HashMap<Product, Integer> cartItems = new HashMap<>();

    public void addItem(ItemName productName, int quantity) throws ProductNotFoundException {
        Product product = productService.getProduct(productName.getItemName());
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
    }

    public double getProductsCost() {
        double sumOfAllProductsWithoutOffer = cartItems.keySet().stream().mapToDouble(p -> p.getUnitPrice() * cartItems.get(p)).sum();

        double totalOffer = offerService.getTotalOfferCostOnProducts(cartItems);
        return sumOfAllProductsWithoutOffer - totalOffer;
    }

    public double getTotalSalesTaxAmount() {
        return (getProductsCost() * productService.getSalesTaxRate()) / 100;
    }

    public double getTotalCost() {
        return getProductsCost() + getTotalSalesTaxAmount();
    }
}
