package com.tmjonker.Java;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(StudentConfig.class);

        Student student = context.getBean(Student.class);

        System.out.println(student.getAdd());
        System.out.println(student.getPh());
        System.out.println(student.getId());
        System.out.println(student.getName());
    }
}
