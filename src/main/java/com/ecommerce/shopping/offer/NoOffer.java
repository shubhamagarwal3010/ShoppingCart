package com.ecommerce.shopping.offer;

import com.ecommerce.shopping.store.Product;

public class NoOffer implements IOffer {

    @Override
    public double getTotalOfferCost(Product product, int totalQuantity) {
        return 0;
    }
}
