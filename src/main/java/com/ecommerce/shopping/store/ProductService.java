package com.ecommerce.shopping.store;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final List<Product> products = new ArrayList<>();

    private double salesTaxRate;

    @Override
    public void addProduct(Product product) throws ProductAlreadyExistsException {
        if (products.stream().noneMatch(p -> p.equals(product))) {
            products.add(product);
            return;
        }
        throw new ProductAlreadyExistsException();
    }

    @Override
    public Product getProduct(String productName) throws ProductNotFoundException {
        Optional<Product> product = products.stream().filter(p -> p.getProductName().equalsIgnoreCase(productName)).findFirst();
        if (product.isPresent()) {
            return product.get().clone();
        }
        throw new ProductNotFoundException();
    }

    @Override
    public double getProductPrice(String productName) throws ProductNotFoundException {
        Optional<Product> product = products.stream().filter(p -> p.getProductName().equalsIgnoreCase(productName)).findFirst();
        if (product.isPresent()) {
            return product.get().getUnitPrice();
        }
        throw new ProductNotFoundException();
    }

    @Override
    public double getSalesTaxRate() {
        return salesTaxRate;
    }

    @Override
    public void setSalesTaxRate(double salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }
}
