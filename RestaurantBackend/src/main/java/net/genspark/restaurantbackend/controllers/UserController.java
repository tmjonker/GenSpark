package net.genspark.restaurantbackend.controllers;

import net.genspark.restaurantbackend.entities.address.Address;
import net.genspark.restaurantbackend.services.CustomUserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    CustomUserDetailsService userDetailsService;

    public UserController(CustomUserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/user/{username}")
    public List<Address> getUserAddresses(@PathVariable String username) {

        return userDetailsService.getUserAddresses(username);
    }
}
