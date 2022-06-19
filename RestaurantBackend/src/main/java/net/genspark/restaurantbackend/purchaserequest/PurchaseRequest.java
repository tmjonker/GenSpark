package net.genspark.restaurantbackend.purchaserequest;

import net.genspark.restaurantbackend.entities.address.Address;
import net.genspark.restaurantbackend.entities.menu.MenuItem;

import javax.persistence.ManyToMany;
import java.util.List;

public class PurchaseRequest {

    private String date;

    private double price;

    private String username;

    private List<Integer> menuIds;

    private Address address;

    public PurchaseRequest(String date, double price, String username, List<Integer> menuIds) {
        this.date = date;
        this.price = price;
        this.username = username;
        this.menuIds = menuIds;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuId) {
        this.menuIds = menuId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
