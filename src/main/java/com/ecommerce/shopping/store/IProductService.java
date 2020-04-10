package com.ecommerce.shopping.store;

public interface IProductService {
    void addProduct(Product product) throws ProductAlreadyExistsException;

    Product getProduct(String productName) throws ProductNotFoundException;

    double getProductPrice(String productName) throws ProductNotFoundException;

    double getSalesTaxRate();

    void setSalesTaxRate(double salesTaxRate);
}
