package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.data.FileStorageRepository;
import com.onlinestore.onlinestore.data.ProductRepository;
import com.onlinestore.onlinestore.model.Product;
import com.onlinestore.onlinestore.service.ProductService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

@Controller
@RequestMapping("/products")
public class ProductsController {

    public static final String DISPO = """
            attachment; filename="%s"
            """;
    private ProductRepository productRepository;
    private FileStorageRepository fileStorageRepository;
    private ProductService productService;

    public ProductsController(ProductRepository productRepository, FileStorageRepository fileStorageRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.fileStorageRepository = fileStorageRepository;
        this.productService = productService;
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

    @GetMapping("/images/{resource}")
    public ResponseEntity<Resource> getResource(@PathVariable String resource) {
        return   ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, format(DISPO,resource))
                .body(fileStorageRepository.findByName(resource));
    }

    @GetMapping("/edit/Id={id}")
    public String showEditPage(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public ModelAndView saveEditedProduct(Product product, @RequestParam MultipartFile photoFile) throws IOException {
        productService.save(product, photoFile.getInputStream());
        return new ModelAndView("redirect:http://localhost:8080/products");
    }

    @GetMapping("/addproduct")
    public String showAddProductPage() {
        return "products/addproduct";
    }

    @PostMapping("/addproduct")
    public ModelAndView addProduct(Product product, @RequestParam MultipartFile photoFile) throws IOException {
        productService.save(product, photoFile.getInputStream());
        return new ModelAndView("redirect:http://localhost:8080/products");
    }

    @GetMapping("/delete/Id={id}")
    public ModelAndView deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return new ModelAndView("redirect:http://localhost:8080/products");
    }


}