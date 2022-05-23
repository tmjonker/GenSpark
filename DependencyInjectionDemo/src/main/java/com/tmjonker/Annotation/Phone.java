package com.tmjonker.Annotation;

import org.springframework.stereotype.Component;

@Component
public class Phone {

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
