package com.ordonia.Lab_7;

import com.ordonia.Lab_7.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private long idCounter = 4;

    public ProductService() {
        productList.add(new Product(1L, "IphoneXR", 10999.99));
        productList.add(new Product(2L, "Iphone12ProMax", 29999.99));
        productList.add(new Product(3L, "Iphone11ProMax", 11999.99));
        productList.add(new Product(3L, "Iphone13", 19999.99));
    }

    public List<Product> findAll() {
        return productList;
    }

    public Optional<Product> findById(Long id) {
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Product save(Product product) {
        product.setId(idCounter++);
        productList.add(product);
        return product;
    }

    public Optional<Product> update(Long id, Product newProductData) {
        return findById(id).map(existingProduct -> {
            existingProduct.setName(newProductData.getName());
            existingProduct.setPrice(newProductData.getPrice());
            return existingProduct;
        });
    }

    public boolean delete(Long id) {
        return productList.removeIf(p -> p.getId().equals(id));
    }
}