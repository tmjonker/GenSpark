package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.address.Address;
import net.genspark.restaurantbackend.entities.user.User;
import net.genspark.restaurantbackend.repositories.UserRepository;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public List<Address> getUserAddresses(String username) {

        User user = (User) loadUserByUsername(username);

        return user.getAddresses();
    }

    public boolean userHasAddress(User user, Address address) {

        List<Address> addressList = user.getAddresses();

        for (Address add : addressList) {

            if (add.getAddress1().equals(address.getAddress1()) && add.getZipCode() == address.getZipCode())
                return true;
        }

        return false;
    }
}
