package net.genspark.restaurantbackend.commandlinerunners;

import net.genspark.restaurantbackend.entities.menu.MenuItem;
import net.genspark.restaurantbackend.services.MenuService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MenuCommandLineRunner implements CommandLineRunner {

    private MenuService menuService;

    public MenuCommandLineRunner(MenuService menuService) {

        this.menuService = menuService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!menuService.existsById(10)) {

            MenuItem menuItem1 = new MenuItem(10, "Appetizer", "Onion Ring Tower", "Tower of Onion Rings",
                    7.99, "none-right-now");
            MenuItem menuItem2 = new MenuItem(11, "Appetizer", "Mozzarella Sticks", "Mozzarella sticks with marinara sauce",
                    7.99, "none-right-now");
            MenuItem menuItem3 = new MenuItem(12, "Appetizer", "Boneless Wings", "Boneless wings with ranch or bleu cheese",
                    8.99, "none-right-now");

            menuService.addMenuItem(menuItem1);
            menuService.addMenuItem(menuItem2);
            menuService.addMenuItem(menuItem3);
        }
    }
}
