package com.ordonia.Lab_7;

import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQLController {

    private final ProductService productService;

    public ProductGraphQLController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> products() {
        return productService.findAll();
    }

    @QueryMapping
    public Product productById(@Argument Long id) {
        return productService.findById(id).orElse(null);
    }

    // ===== MUTATIONS =====
    @MutationMapping
    public Product createProduct(@Argument String name, @Argument double price) {
        Product p = new Product(null, name, price);
        return productService.save(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id,
                                 @Argument String name,
                                 @Argument double price) {
        Product updatedData = new Product(id, name, price);
        return productService.update(id, updatedData).orElse(null);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        return productService.delete(id);
    }
}
