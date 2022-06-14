package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.menu.MenuItem;
import net.genspark.restaurantbackend.exceptions.MenuItemNotFoundException;
import net.genspark.restaurantbackend.repositories.MenuItemRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MenuService {

    MenuItemRepository menuItemRepository;

    public MenuService(MenuItemRepository menuRepository) {

        this.menuItemRepository = menuRepository;
    }

    public List<MenuItem> getAllMenuItems() {

        return Streamable.of(menuItemRepository.findAll()).toList();
    }

    public MenuItem getMenuItemByName(String name) {

        return menuItemRepository.findByName(name).orElseThrow(() -> new MenuItemNotFoundException(name));
    }

    public MenuItem getMenuItemById(Integer id) {

        return menuItemRepository.findById(id).orElseThrow(() -> new MenuItemNotFoundException(id));
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {

        return menuItemRepository.findAllByCategory(category).orElseThrow(() -> new MenuItemNotFoundException(category));
    }

    public MenuItem addMenuItem(MenuItem menuItem) {

        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Integer id) {

        menuItemRepository.deleteById(id);
    }

    public boolean existsById(int id) {

        return menuItemRepository.existsById(id);
    }
}
