package net.genspark.restaurantbackend.controllers;

import net.genspark.restaurantbackend.entities.user.User;
import net.genspark.restaurantbackend.services.CustomUserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    CustomUserDetailsService userDetailsService;

    public RegisterController(CustomUserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }
    @PostMapping("/register")
    public User addUser(@RequestBody User user) {

        return userDetailsService.addUser(user);
    }
}
