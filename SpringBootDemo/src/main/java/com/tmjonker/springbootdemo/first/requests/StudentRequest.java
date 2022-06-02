package com.tmjonker.springbootdemo.first.requests;

public class StudentRequest {

    private String firstName;
    private String lastName;

    public StudentRequest() {
    }

    public StudentRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
