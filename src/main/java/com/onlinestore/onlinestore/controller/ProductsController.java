package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.data.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductRepository productRepository;
}
