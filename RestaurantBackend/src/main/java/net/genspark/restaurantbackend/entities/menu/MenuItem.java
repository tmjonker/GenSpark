package net.genspark.restaurantbackend.entities.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuItem {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String imgPath;

    public MenuItem() {
    }

    public MenuItem(Integer id, String category, String name, String description, double price, String imgPath) {

        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imagePath) {
        this.imgPath = imagePath;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
