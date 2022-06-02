package com.tmjonker.springbootdemo.second.controllers;

import com.tmjonker.springbootdemo.second.entities.Car;
import com.tmjonker.springbootdemo.second.requests.CarRequest;
import com.tmjonker.springbootdemo.second.services.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarsController {

    private CarService carService;

    public CarsController(CarService carService) {

        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getCars() {

        return carService.retrieveAllCars();
    }

    @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable int id) {

        return carService.retrieveCarById(id);
    }

    @GetMapping("/cars/make/{make}")
    public List<Car> getCarsByMake(@PathVariable String make) {

        return carService.retrieveCarsByMake(make);
    }

    @GetMapping("/cars/model/{model}")
    public List<Car> getCarsByModel(@PathVariable String model) {

        return carService.retrieveCarsByModel(model);
    }

    @PostMapping("/cars")
    public Car postCar(@RequestBody CarRequest carRequest) {

        return carService.addCar(carRequest);
    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@RequestBody CarRequest carRequest, @PathVariable int id) {

        return carService.updateCar(carRequest, id);
    }

    @DeleteMapping("/cars/{id}")
    public String deleteCarById(@PathVariable int id) {

        carService.deleteCar(id);

        return "If a car with that ID existed, it has been deleted";
    }
}
