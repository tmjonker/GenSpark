package com.tmjonker.springbootdemo.first.repositories;

import com.tmjonker.springbootdemo.first.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {


}
