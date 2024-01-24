package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.data.ProductRepository;
import com.onlinestore.onlinestore.model.Product;
import com.onlinestore.onlinestore.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String showProductsPage() {
        return "products";
    }
    @ModelAttribute("products")
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @ModelAttribute
    public Product getProduct() {
        return new Product();
    }

    @PostMapping
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:products";
    }


}
