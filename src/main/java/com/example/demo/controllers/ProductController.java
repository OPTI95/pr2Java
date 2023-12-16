package com.example.demo.controllers;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> products = productDao.index();
        model.addAttribute("products", products);
        return "product/productList";
    }

    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Product product = productDao.show(id);
        model.addAttribute("product", product);
        return "product/productDetails";
    }

    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/productForm";
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productDao.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productDao.show(id);
        model.addAttribute("product", product);
        return "product/editProductForm";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product updatedProduct) {
        productDao.update(id, updatedProduct);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productDao.delete(id);
        return "redirect:/products";
    }
}
