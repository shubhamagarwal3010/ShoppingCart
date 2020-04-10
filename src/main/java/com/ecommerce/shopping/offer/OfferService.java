package com.ecommerce.shopping.offer;

import com.ecommerce.shopping.store.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OfferService {
    private HashMap<Product, IOffer> offers = new HashMap<>();

    public double getTotalOfferCostOnProducts(HashMap<Product, Integer> cartItems) {
        return cartItems
                .entrySet()
                .stream()
                .mapToDouble(p -> offers.getOrDefault(p.getKey(), new NoOffer()).getTotalOfferCost(p.getKey(), p.getValue()))
                .sum();
    }

    public void setOffer(Product product, IOffer offer) {
        offers.put(product, offer);
    }
}
