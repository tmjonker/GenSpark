package net.genspark.restaurantbackend.repositories;

import net.genspark.restaurantbackend.entities.menu.MenuItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {

    Optional<MenuItem> findByName(String name);
    Optional<List<MenuItem>> findAllByCategory(String category);
}
