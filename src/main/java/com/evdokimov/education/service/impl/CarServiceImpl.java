package com.evdokimov.education.service.impl;

import com.evdokimov.education.model.Car;
import com.evdokimov.education.repository.CarRepository;
import com.evdokimov.education.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.delete(id);
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

}
