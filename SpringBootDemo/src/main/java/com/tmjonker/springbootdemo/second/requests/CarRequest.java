package com.tmjonker.springbootdemo.second.requests;

public class CarRequest {

    private String make;
    private String model;

    public CarRequest(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public CarRequest() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
