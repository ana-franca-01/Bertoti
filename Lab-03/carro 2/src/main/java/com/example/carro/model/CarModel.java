package com.example.carro.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class CarModel {

        @Id
        private String id;

        private String name;

        }

