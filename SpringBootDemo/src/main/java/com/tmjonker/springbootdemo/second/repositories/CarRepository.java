package com.tmjonker.springbootdemo.second.repositories;

import com.tmjonker.springbootdemo.second.entities.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Integer> {

    Optional<List<Car>> findByMake(String make);

    Optional<List<Car>> findByModel(String model);
}
