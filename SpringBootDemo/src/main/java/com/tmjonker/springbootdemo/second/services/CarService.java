package com.tmjonker.springbootdemo.second.services;

import com.tmjonker.springbootdemo.second.entities.Car;
import com.tmjonker.springbootdemo.second.repositories.CarRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository) {

        this.carRepository = carRepository;
    }

    public List<Car> retrieveCarsByModel(String model) {

        return carRepository.findByModel(model).orElseThrow(() -> new RuntimeException("No Cars with that model were found."));
    }

    public List<Car> retrieveCarsByMake(String make) {

        return carRepository.findByMake(make).orElseThrow(() -> new RuntimeException("No Cars with that Make were found."));
    }

    public List<Car> retrieveAllCars() {

        return Streamable.of(carRepository.findAll()).toList();
    }

    public Car retrieveCarById(int id) {

        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("No Cars with that id were found."));
    }

    public Car addCar(Map<String, String> carMap) {

        Car car = new Car(carMap.get("make"), carMap.get("model"));

        return carRepository.save(car);
    }

    public Car updateCar(Map<String, String> carMap, int id) {

        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("No Cars with that id were found."));
        car.setMake(carMap.get("make"));
        car.setModel(carMap.get("model"));

        return carRepository.save(car);
    }

    public void deleteCar(int id) {

        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("No Cars with that id were found."));

        carRepository.delete(car);
    }
}
