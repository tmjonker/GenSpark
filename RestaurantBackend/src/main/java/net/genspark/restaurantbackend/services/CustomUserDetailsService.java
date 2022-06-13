package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.user.User;
import net.genspark.restaurantbackend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordManagementService passwordManagementService;

    public CustomUserDetailsService(UserRepository userRepository, PasswordManagementService passwordManagementService) {

        this.userRepository = userRepository;
        this.passwordManagementService = passwordManagementService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username"));
    }

    public User addUser(User user) {

        String password = passwordManagementService.encodePassword(user.getPassword());
        user.setPassword(password);

        return userRepository.save(user);
    }
}
