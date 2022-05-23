package com.tmjonker.XML;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");

        Student student = (Student) context.getBean("Student");

        System.out.println(student.getAdd());
        System.out.println(student.getPh());
        System.out.println(student.getId());
        System.out.println(student.getName());
    }
}
