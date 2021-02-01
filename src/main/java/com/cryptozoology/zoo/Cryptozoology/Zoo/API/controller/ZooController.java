package com.cryptozoology.zoo.Cryptozoology.Zoo.API.controller;

import com.cryptozoology.zoo.Cryptozoology.Zoo.API.model.Animal;
import com.cryptozoology.zoo.Cryptozoology.Zoo.API.service.ZooService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZooController {

    ZooService zooService;

    public ZooController(ZooService zooService) {
        this.zooService = zooService;
    }

    @PostMapping("/animal")
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal){
        return new ResponseEntity<Animal>(zooService.addAnimal(animal), HttpStatus.CREATED);
    }
}
