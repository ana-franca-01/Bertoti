package com.example.carro.controller;

import com.example.carro.model.CarModel;
import com.example.carro.repository.CarRepository;
import com.example.carro.service.CarService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carros")

public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public List<CarModel> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CarModel getById(@PathVariable String id) {
        return service.findById(id).orElse(null);
    }

    @PostMapping
    public CarModel create(@RequestBody CarModel carro) {
        return service.save(carro);
    }

    @PutMapping("/{id}")
    public CarModel update(@PathVariable String id, @RequestBody CarModel carro) {
        carro.setId(id);
        return service.save(carro);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}