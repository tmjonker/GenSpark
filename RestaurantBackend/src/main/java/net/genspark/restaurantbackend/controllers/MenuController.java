package net.genspark.restaurantbackend.controllers;

import net.genspark.restaurantbackend.entities.menu.MenuItem;
import net.genspark.restaurantbackend.entities.menu.category.Category;
import net.genspark.restaurantbackend.exceptions.MenuItemNotFoundException;
import net.genspark.restaurantbackend.repositories.MenuItemRepository;
import net.genspark.restaurantbackend.services.MenuService;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class MenuController {

    MenuService menuService;

    public MenuController(MenuService menuService) {

        this.menuService = menuService;
    }

    @GetMapping("/api/menu")
    public List<MenuItem> getAllMenuItems() {

        return menuService.getAllMenuItems();
    }

    @GetMapping("/api/menu/name/{name}")
    public MenuItem getMenuItemByName(@PathVariable String name) {

        return menuService.getMenuItemByName(name);
    }

    @GetMapping("/api/menu/id/{id}")
    public MenuItem getMenuItemById(@PathVariable Integer id) {

        return menuService.getMenuItemById(id);
    }

    @GetMapping("/api/menu/category/{category}")
    public List<MenuItem> getMenuItemsByCategory(@PathVariable String category) {

        return menuService.getMenuItemsByCategory(category);
    }

    @GetMapping("/api/menu/categories")
    public List<Category> getCategories() {

        return menuService.getCategories();
    }

    @PostMapping(value = "/api/menu/{id}")
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {

        return menuService.addMenuItem(menuItem);
    }

    @DeleteMapping("/menu/{id}")
    public void deleteMenuItem(@PathVariable Integer id) {

        menuService.deleteMenuItem(id);
    }
}
