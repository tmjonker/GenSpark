package net.genspark.restaurantbackend.entities.purchase;

import net.genspark.restaurantbackend.entities.menu.MenuItem;
import net.genspark.restaurantbackend.entities.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private double price;

    @ManyToMany
    @JoinTable(
            name = "purchases_menu",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<MenuItem> menuItems;

    public Purchase(String date, double price) {
        this.date = date;
        this.price = price;
        menuItems = new ArrayList<>();
    }

    public Purchase() {
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", menuItems=" + menuItems +
                '}';
    }
}
