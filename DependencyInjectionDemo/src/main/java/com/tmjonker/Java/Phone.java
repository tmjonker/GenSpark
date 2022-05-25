package com.tmjonker.Java;


import org.springframework.beans.factory.annotation.Value;

public class Phone {

    @Value("#{'202-779-1008'}")
    private String mob;

    public void setMob(String mob) {
        this.mob = mob;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "mob='" + mob + '\'' +
                '}';
    }
}
