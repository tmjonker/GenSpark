package com.tmjonker.springbootdemo.first.services;

import com.tmjonker.springbootdemo.first.entities.Student;
import com.tmjonker.springbootdemo.first.repositories.StudentRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public List<Student> retrieveAllStudents() {

        return Streamable.of(studentRepository.findAll()).toList();
    }

    public Student addStudent(Map<String, String> studentMap) {

        Student student = new Student(studentMap.get("firstName"), studentMap.get("lastName"));

        return studentRepository.save(student);
    }

    public Student updateStudent(Map<String, String> studentMap, int id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));

        student.setFirstName(studentMap.get("firstName"));
        student.setLastName(studentMap.get("lastName"));

        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));

        studentRepository.delete(student);
    }
}
