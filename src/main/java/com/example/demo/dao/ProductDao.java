package com.example.demo.dao;
import com.example.demo.model.Product;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDao {

    private static Long PRODUCT_COUNT = 0L;
    private List<Product> products;

    {
        products = new ArrayList<>();

        products.add(new Product(++PRODUCT_COUNT, "Laptop", "Powerful laptop for work", 999.99));
        products.add(new Product(++PRODUCT_COUNT, "Smartphone", "High-end smartphone", 599.99));
    }

    public List<Product> index() {
        return products;
    }

    public Product show(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findAny().orElse(null);
    }

    public void save(Product product) {
        product.setId(++PRODUCT_COUNT);
        products.add(product);
    }

    public void update(Long id, Product updatedProduct) {
        Product existingProduct = show(id);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
        }
    }

    public void delete(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
