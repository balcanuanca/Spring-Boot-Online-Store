package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.data.FileStorageRepository;
import com.onlinestore.onlinestore.data.ProductRepository;
import com.onlinestore.onlinestore.model.Product;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static java.lang.String.format;

@Controller
@RequestMapping("/products")
public class ProductsController {

    public static final String DISPO = """
            attachment; filename="%s"
            """;
    private ProductRepository productRepository;
    private FileStorageRepository fileStorageRepository;

    public ProductsController(ProductRepository productRepository, FileStorageRepository fileStorageRepository) {
        this.productRepository = productRepository;
        this.fileStorageRepository = fileStorageRepository;
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
    public String saveProduct(Product product, @RequestParam MultipartFile photoFile) throws IOException {
        productRepository.save(product);
        fileStorageRepository.save(photoFile.getOriginalFilename(),photoFile.getInputStream());
        return "redirect:products";
    }

    @GetMapping("/images/{resource}")
    public ResponseEntity<Resource> getResource(@PathVariable String resource) {
        return   ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, format(DISPO,resource))
                .body(fileStorageRepository.findByName(resource));
    }

}