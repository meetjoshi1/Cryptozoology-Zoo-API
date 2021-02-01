package com.cryptozoology.zoo.Cryptozoology.Zoo.API.service;

import com.cryptozoology.zoo.Cryptozoology.Zoo.API.model.Animal;
import com.cryptozoology.zoo.Cryptozoology.Zoo.API.repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZooService {

    ZooRepository zooRepository;

    public ZooService(ZooRepository zooRepository) {
        this.zooRepository = zooRepository;
    }

    public Animal addAnimal(Animal animal) {
        return zooRepository.save(animal);
    }

    public List<Animal> getAllAnimals() {
        return zooRepository.findAll();
    }

    public Animal setAnimalMood(Long id) {
        for(Animal animal : zooRepository.findAll()){
            if(animal.getId().equals(id)){
                animal.setMood(Boolean.TRUE);
                return animal;
            }
        }
        return null;
    }
}
