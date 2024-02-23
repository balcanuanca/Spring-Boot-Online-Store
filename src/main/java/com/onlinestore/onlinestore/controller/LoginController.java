package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.data.UserRepository;
import com.onlinestore.onlinestore.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    private UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @ModelAttribute
    public User getUser() {
        return new User();
    }

    @GetMapping("/sign-up")
    public String showSignUpPage() {
        return "login/sign-up";
    }

    @PostMapping("/sign-up")
    public ModelAndView saveUser(User user) {
        userRepository.save(user);
        return new ModelAndView("redirect:http://localhost:8080/login");
    }
}
