package com.tmjonker.Annotation;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring1.xml");

        Student student = (Student) context.getBean("student");

        System.out.println(student.getAdd());
        System.out.println(student.getPh());
        System.out.println(student.getId());
        System.out.println(student.getName());
    }
}
