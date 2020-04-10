package com.ecommerce.shopping.store;

import java.util.Objects;

public class Product implements Cloneable {

    private final String productName;
    private final double unitPrice;
    private boolean isEligibleForOffer = false;

    public Product(final ItemName productName, final double unitPrice, boolean isEligibleForOffer) {
        this.productName = productName.getItemName();
        this.unitPrice = unitPrice;
        this.isEligibleForOffer = isEligibleForOffer;
    }

    public Product(final ItemName productName, final double unitPrice) {
        this.productName = productName.getItemName();
        this.unitPrice = unitPrice;
    }

    public boolean isEligibleForOffer() {
        return isEligibleForOffer;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    public Product clone() {
        return new Product(ItemName.getItem(this.productName), this.unitPrice, this.isEligibleForOffer);
    }
}
