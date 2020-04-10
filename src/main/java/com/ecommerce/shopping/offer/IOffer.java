package com.ecommerce.shopping.offer;

import com.ecommerce.shopping.store.Product;

public interface IOffer {

    double getTotalOfferCost(Product product, int totalQuantity);
}
