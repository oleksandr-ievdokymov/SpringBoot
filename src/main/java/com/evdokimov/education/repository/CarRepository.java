package com.evdokimov.education.repository;

import com.evdokimov.education.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
