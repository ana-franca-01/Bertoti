package com.example.carro.repository;
import com.example.carro.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
    public interface CarRepository extends JpaRepository<CarModel, String> {
    }

