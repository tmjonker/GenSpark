package net.genspark.restaurantbackend.purchaserequest;

import net.genspark.restaurantbackend.entities.menu.MenuItem;

import javax.persistence.ManyToMany;
import java.util.List;

public class PurchaseRequest {

    private String date;

    private double price;

    private String username;

    private int menuId;

    public PurchaseRequest(String date, double price, String username, int menuId) {
        this.date = date;
        this.price = price;
        this.username = username;
        this.menuId = menuId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
