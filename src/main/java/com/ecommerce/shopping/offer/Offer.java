package com.ecommerce.shopping.offer;

import com.ecommerce.shopping.store.Product;

public class Offer {
    private Product product;
    private boolean isBuyTwoGetOneFree;

    public Offer(Product product, boolean isBuyTwoGetOneFree) {
        this.product = product;
        this.isBuyTwoGetOneFree = isBuyTwoGetOneFree;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isBuyTwoGetOneFree() {
        return isBuyTwoGetOneFree;
    }
}
