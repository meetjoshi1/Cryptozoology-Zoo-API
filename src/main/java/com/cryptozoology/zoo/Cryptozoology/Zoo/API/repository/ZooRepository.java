package com.cryptozoology.zoo.Cryptozoology.Zoo.API.repository;


import com.cryptozoology.zoo.Cryptozoology.Zoo.API.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZooRepository extends JpaRepository<Animal,Long> {
}
