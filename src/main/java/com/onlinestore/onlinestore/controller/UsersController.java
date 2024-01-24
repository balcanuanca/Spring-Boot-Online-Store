package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.data.UserRepository;
import com.onlinestore.onlinestore.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showUsersPage() {
        return "users";
    }

    @ModelAttribute("users")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @ModelAttribute
    public User getUser() {
        return new User();
    }

    @PostMapping
    public String saveUser(User user) {
        userRepository.save(user);
        return "redirect:users";
    }
}
