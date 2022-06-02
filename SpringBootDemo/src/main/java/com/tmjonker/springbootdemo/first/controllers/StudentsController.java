package com.tmjonker.springbootdemo.first.controllers;

import com.tmjonker.springbootdemo.first.entities.Student;
import com.tmjonker.springbootdemo.first.repositories.StudentRepository;
import com.tmjonker.springbootdemo.first.requests.StudentRequest;
import com.tmjonker.springbootdemo.first.services.StudentService;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentsController {

    StudentService studentService;

    public StudentsController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return studentService.retrieveAllStudents();
    }

    @PostMapping("/students")
    public Student postStudent(@RequestBody StudentRequest studentRequest) {

        return studentService.addStudent(studentRequest);
    }

    @PutMapping("/students/{id}")
    public Student putMapping(@RequestBody StudentRequest studentRequest, @PathVariable int id) {

        return studentService.updateStudent(studentRequest, id);
    }

    @DeleteMapping("/students/{id}")
    public String deleteMapping(@PathVariable int id) {

        studentService.deleteStudent(id);

        return "If that student ID existed, then that student has been deleted.";
    }
}
