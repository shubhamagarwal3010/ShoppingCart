package com.ecommerce.shopping.offer;

import com.ecommerce.shopping.store.Product;

public class BuyTwoGetOffer implements IOffer {

    @Override
    public double getTotalOfferCost(Product product, int totalQuantity) {
        int eligibleOfferCount = totalQuantity / 2;
        return product.getUnitPrice() * eligibleOfferCount;
    }
}
