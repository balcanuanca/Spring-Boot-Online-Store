package com.onlinestore.onlinestore.data;

import com.onlinestore.onlinestore.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<ShoppingCart, Long> {
}
