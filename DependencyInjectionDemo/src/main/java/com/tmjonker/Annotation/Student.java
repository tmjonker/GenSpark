package com.tmjonker.Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Student {

    private int id;
    private String name;
    private List<Phone> ph;
    private Address add;

    public Student() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Autowired
    public void setPh(List<Phone> ph) {
        this.ph = ph;
    }

    @Autowired
    public void setAdd(Address add) {
        this.add = add;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Phone> getPh() {
        return ph;
    }

    public Address getAdd() {
        return add;
    }
}




