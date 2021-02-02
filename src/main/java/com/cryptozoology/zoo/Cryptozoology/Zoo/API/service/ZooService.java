package com.cryptozoology.zoo.Cryptozoology.Zoo.API.service;

import com.cryptozoology.zoo.Cryptozoology.Zoo.API.model.Animal;
import com.cryptozoology.zoo.Cryptozoology.Zoo.API.repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Animal animal = zooRepository.findById(id).get();
        animal.setMood(Boolean.TRUE);
        return animal;
    }

    public List<Animal> searchAnimals(Boolean mood, String type) {
        return zooRepository.serachAnimal(mood,type);
    }
}
