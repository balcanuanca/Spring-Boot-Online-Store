package com.onlinestore.onlinestore.data;

import com.onlinestore.onlinestore.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
