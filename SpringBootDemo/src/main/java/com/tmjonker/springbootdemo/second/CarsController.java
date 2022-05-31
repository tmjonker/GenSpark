package com.tmjonker.springbootdemo.second;

import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CarsController {

    private CarRepository carRepository;

    public CarsController(CarRepository carRepository) {

        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {

        return Streamable.of(carRepository.findAll()).toList();
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable int id) {

        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("No Cars with that id were found."));
    }

    @GetMapping("/cars/make/{make}")
    public List<Car> getCarsByMake(@PathVariable String make) {

        return carRepository.findByMake(make).orElseThrow(() -> new RuntimeException("No Cars with that Make were found."));
    }

    @GetMapping("/cars/model/{model}")
    public List<Car> getCarsByModel(@PathVariable String model) {

        return carRepository.findByModel(model).orElseThrow(() -> new RuntimeException("No Cars with that model were found."));
    }

    @PostMapping("/cars")
    public Car postCar(@RequestBody Map<String, String> carMap) {

        Car car = new Car(carMap.get("make"), carMap.get("model"));

        return carRepository.save(car);
    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@RequestBody Map<String, String> carMap, @PathVariable int id) {

        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("No Cars with that id were found."));
        car.setMake(carMap.get("make"));
        car.setModel(carMap.get("model"));

        return carRepository.save(car);
    }

    @DeleteMapping("/cars/{id}")
    public String deleteCarById(@PathVariable int id) {

        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("No Cars with that id were found."));

        carRepository.delete(car);

        return "If a car with that ID existed, it has been deleted";
    }
}
