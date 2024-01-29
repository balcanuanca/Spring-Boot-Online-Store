package com.onlinestore.onlinestore.data;

import com.onlinestore.onlinestore.model.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;

//@Component
public class UserDataLoader implements ApplicationRunner {
    private UserRepository userRepository;

    public UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() == 0) {
            List<User> users = List.of(
//                    new User(null, "Bart","Simpson", "dummy.data@mail.com"),
//                    new User(null, "Pete","Gray", "dummy.data@mail.com"),
//                    new User(null, "Sarah","Smith", "dummy.data@mail.com"),
//                    new User(null, "Sam","Smart", "dummy.data@mail.com"),
//                    new User(null, "Lizzie","Jackson", "dummy.data@mail.com"),
//                    new User(null, "Bobby","Brown", "dummy.data@mail.com")
            );
            userRepository.saveAll(users);
        }
    }
}
