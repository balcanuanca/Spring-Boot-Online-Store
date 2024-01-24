package com.onlinestore.onlinestore.data;

import com.onlinestore.onlinestore.model.Product;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

//@Component
public class ProductDataLoader implements ApplicationRunner {

    private ProductRepository productRepository;

    public ProductDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
//                    new Product(null, "Shirt", new BigDecimal(100), "Size L" ),
//                    new Product(null, "Pants", new BigDecimal(230), "Size 32" ),
//                    new Product(null, "Hat", new BigDecimal(40), "One size" ),
//                    new Product(null, "Socks", new BigDecimal(20), "37-39" )
            );
            productRepository.saveAll(products);
        }
    }
}
