package com.tmjonker.Java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class Address {

    @Value("#{'Manassas'}")
    private String city;
    @Value("#{'VA'}")
    private String state;
    @Value("#{'USA'}")
    private String country;
    @Value("#{20111}")
    private String zipcode;

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
