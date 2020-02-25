package com.ecommerce.shopping.store;

public interface IProductService {
    public void addProduct(Product product) throws ProductAlreadyExists;

    Product getProduct(String productName) throws ProductNotFoundException;

    public double getProductPrice(String productName) throws ProductNotFoundException;

    double getSalesTaxRate();

    void setSalesTaxRate(double salesTaxRate);
}
