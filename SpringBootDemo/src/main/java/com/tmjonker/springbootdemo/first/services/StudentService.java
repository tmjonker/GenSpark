package com.tmjonker.springbootdemo.first.services;

import com.tmjonker.springbootdemo.first.entities.Student;
import com.tmjonker.springbootdemo.first.repositories.StudentRepository;
import com.tmjonker.springbootdemo.first.requests.StudentRequest;
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

    public Student addStudent(StudentRequest studentRequest) {

        Student student = new Student(studentRequest.getFirstName(), studentRequest.getLastName());

        return studentRepository.save(student);
    }

    public Student updateStudent(StudentRequest studentRequest, int id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));

        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());

        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));

        studentRepository.delete(student);
    }
}
