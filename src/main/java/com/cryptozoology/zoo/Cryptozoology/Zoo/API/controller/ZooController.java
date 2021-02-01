package com.cryptozoology.zoo.Cryptozoology.Zoo.API.controller;

import com.cryptozoology.zoo.Cryptozoology.Zoo.API.model.Animal;
import com.cryptozoology.zoo.Cryptozoology.Zoo.API.service.ZooService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZooController {

    ZooService zooService;

    public ZooController(ZooService zooService) {
        this.zooService = zooService;
    }

    @GetMapping("/animalList")
    public ResponseEntity<List<Animal>> getAllAnimals(){
        return new ResponseEntity<List<Animal>>(zooService.getAllAnimals(), HttpStatus.OK);
    }

    @PostMapping("/animal")
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal){
        return new ResponseEntity<Animal>(zooService.addAnimal(animal), HttpStatus.CREATED);
    }

    @PutMapping("/updateMood/{id}")
    public ResponseEntity<Animal> setAnimalMood(@PathVariable Long id){
        return new ResponseEntity<Animal>(zooService.setAnimalMood(id), HttpStatus.OK);

    }
}
