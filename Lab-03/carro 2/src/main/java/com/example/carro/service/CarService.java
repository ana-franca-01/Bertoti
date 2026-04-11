package com.example.carro.service;

import com.example.carro.model.CarModel;
import com.example.carro.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CarService {





        private final CarRepository repository;

        public CarService(CarRepository repository) {
            this.repository = repository;
        }

        public List<CarModel> findAll() {
            return repository.findAll();
        }

        public Optional<CarModel> findById(String id) {
            return repository.findById(id);
        }

        public CarModel save(CarModel carro) {
            return repository.save(carro);
        }

        public void delete(String id) {
            repository.deleteById(id);
        }
    }

