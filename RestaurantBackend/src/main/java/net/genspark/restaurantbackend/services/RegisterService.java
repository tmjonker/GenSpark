package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.user.User;
import net.genspark.restaurantbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


}
