package com.tmjonker.Java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public Student getStudent() {
        return new Student(2333695, "Tim Jonker");
    }

    @Bean
    public Address getAddress() {
        Address address = new Address();
        address.setCity("Manassas");
        address.setCountry("USA");
        address.setState("VA");
        address.setZipcode("20111");

        return address;
    }

    @Bean
    public Phone getPhoneNumber() {

        Phone phone = new Phone();
        phone.setMob("202-779-1008");
        return phone;
    }
}
