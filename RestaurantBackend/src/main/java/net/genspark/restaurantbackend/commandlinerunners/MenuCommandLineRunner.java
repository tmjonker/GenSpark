package net.genspark.restaurantbackend.commandlinerunners;

import net.genspark.restaurantbackend.entities.menu.MenuItem;
import net.genspark.restaurantbackend.services.MenuService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuCommandLineRunner implements CommandLineRunner {

    private MenuService menuService;

    public MenuCommandLineRunner(MenuService menuService) {

        this.menuService = menuService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<MenuItem> items = getMenuItems();

        for(MenuItem item : items) {
            if(!menuService.existsById(item.getId()))
                menuService.addMenuItem(item);
        }

    }

    private List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem(10, "Appetizer", "Onion Ring Tower", "Tower of Onion Rings",
                7.99, "https://media-cdn.tripadvisor.com/media/photo-s/07/4d/94/83/a-tower-of-onion-rings.jpg"));
        items.add(new MenuItem(11, "Appetizer", "Mozzarella Sticks", "Mozzarella sticks with marinara sauce",
                7.99, "https://static.onecms.io/wp-content/uploads/sites/43/2022/04/07/23596-fried-mozzarella-cheese-sticks-3x2-252.jpg"));
        items.add(new MenuItem(12, "Appetizer", "Boneless Wings", "Boneless wings with ranch or bleu cheese",
                8.99, "https://lilluna.com/wp-content/uploads/2018/05/honey-bbq-wings-resize-5.jpg"));

        items.add(new MenuItem(1, "Burger", "Double Cheeseburger", "Two beef patties with American cheese",
                3.99, "https://www.carlsjr.com/-/media/project/cke/carlsjr/products/charboiled-burgers/cj_site_705x742_doublecheeseburger.jpg"));
        items.add(new MenuItem(2, "Burger", "Cheeseburger", "One beef patty with American cheese",
                2.49, "https://www.hamburgerstand.com/wp-content/uploads/2015/03/burgers-cheeseburger-main.jpg"));
        items.add(new MenuItem(3, "Burger", "Triple Stack", "Three beef patties with American cheese",
                5.99, "https://images.fineartamerica.com/images-medium-large-5/large-beef-burger-with-cheese-and-salad-mark-sykes.jpg"));

        items.add(new MenuItem(4, "Pizza", "Supreme", "14-inch with pepperoni, sausage, ham, onion, mushroom, and bell pepper",
                14.99, "https://restaurantclicks.com/wp-content/uploads/2021/09/tasty-supreme-pizza-with-olives-peppers-onions-and-sausage.jpg"));
        items.add(new MenuItem(5, "Pizza", "Pepperoni", "14-inch with double pepperoni",
                12.99, "https://cdn.vox-cdn.com/thumbor/fHcMf9a4S7iaY2zakdIENd2yXYA=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/10235609/pepperoni.jpeg"));
        items.add(new MenuItem(6, "Pizza", "Cheese", "14-inch with extra cheese",
                10.99, "https://lh3.googleusercontent.com/-1Iil24qU-EM/VqnJ50T5vII/AAAAAAAFQzI/O2jXEMQXKt0/s800-Ic42/cheese-pizza-2.jpg"));

        items.add(new MenuItem(7, "Sides", "French Fries", "Small cup of fresh, golden-brown french fries",
                1.99, "https://upload.wikimedia.org/wikipedia/commons/8/83/French_Fries.JPG"));
        items.add(new MenuItem(8, "Sides", "Salad", "Small Bowl - Caesar Salad",
                1.99, "https://www.jessicagavin.com/wp-content/uploads/2019/07/caesar-salad-10-1200.jpg"));
        items.add(new MenuItem(9, "Sides", "Baked Potato", "Loaded Baked Potato with cheese, bacon bits, and sour cream",
                1.99, "https://www.orwhateveryoudo.com/wp-content/uploads/2020/07/Smoked-Loaded-Baked-Potato-12-720x720.jpg"));

        items.add(new MenuItem(13, "Dessert", "Raspberry Cheesecake", "Slice of cheesecake with raspberry topping",
                5.99, "https://bakingamoment.com/wp-content/uploads/2019/08/white-chocolate-raspberry-cheesecake.jpg"));
        items.add(new MenuItem(14, "Dessert", "Dessert Pizza", "5-inch pizza with cinnamon, chocolate chips, and whipped cream",
                5.99, "https://sugarspunrun.com/wp-content/uploads/2018/04/Chocolate-Chip-Dessert-Pizza-Recipe-1-of-1-8.jpg"));
        items.add(new MenuItem(15, "Dessert", "Ice Cream Sundae", "3-scoops of vanilla ice cream with chocolate syrup, " +
                "whipped cream, and 1 cherry on top",
                4.99, "https://izzycooking.com/wp-content/uploads/2021/05/Ice-Cream-Sundae-thumbnail.jpg"));

        return items;
    }



}
