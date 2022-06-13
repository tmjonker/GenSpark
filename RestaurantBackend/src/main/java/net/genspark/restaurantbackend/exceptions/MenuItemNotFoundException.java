package net.genspark.restaurantbackend.exceptions;

public class MenuItemNotFoundException extends RuntimeException {

    public MenuItemNotFoundException(String name) {

        super("Menu Item Not Found: " + name);
    }

    public MenuItemNotFoundException(Integer id) {

        super("Menu Item Not Found: " + id);
    }
}
