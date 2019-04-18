package com.evdokimov.education.controller;

import com.evdokimov.education.model.Car;
import com.evdokimov.education.security.jwt.SecurityUtils;
import com.evdokimov.education.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/th")
    public String getCars(Map<String, Object> model, @RequestHeader HttpHeaders httpHeaders){
        model.put("cars", carService.getAllCars());
        Map<String, String> headerMap = httpHeaders.toSingleValueMap();
        headerMap.put("Authorization", SecurityUtils.getCurrentUserJWT());
        return "home";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

}
