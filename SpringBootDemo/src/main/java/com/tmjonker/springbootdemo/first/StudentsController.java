package com.tmjonker.springbootdemo.first;

import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentsController {

    StudentRepository studentRepository;

    public StudentsController(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return Streamable.of(studentRepository.findAll()).toList();
    }

    @PostMapping("/students")
    public Student postStudent(@RequestBody Map<String, String> studentMap) {

        Student student = new Student(studentMap.get("firstName"), studentMap.get("lastName"));

        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public Student putMapping(@RequestBody Map<String, String> studentMap, @PathVariable int id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));

        student.setFirstName(studentMap.get("firstName"));
        student.setLastName(studentMap.get("lastName"));

        return studentRepository.save(student);
    }

    @DeleteMapping("/students/{id}")
    public String deleteMapping(@PathVariable int id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));

        studentRepository.delete(student);

        return "If that student ID existed, then that student has been deleted.";
    }
}
