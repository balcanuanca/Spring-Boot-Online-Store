package com.onlinestore.onlinestore.data;

import com.onlinestore.onlinestore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
