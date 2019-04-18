package com.evdokimov.education.service;

import com.evdokimov.education.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    void deleteCarById(Long id);

    Car createCar(Car car);
}
