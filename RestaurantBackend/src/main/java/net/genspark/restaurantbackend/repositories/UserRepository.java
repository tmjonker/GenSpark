package net.genspark.restaurantbackend.repositories;

import net.genspark.restaurantbackend.entities.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
