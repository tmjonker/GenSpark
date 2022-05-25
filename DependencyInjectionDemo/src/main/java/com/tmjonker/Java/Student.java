package com.tmjonker.Java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class Student {

    @Value("#{2333695}")
    private int id;
    @Value("#{'Tim Jonker'}")
    private String name;
    private List<Phone> ph;
    private Address add;


    public Student() {}
    public Student(int id, String name) {

        this.id = id;
        this.name = name;
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




