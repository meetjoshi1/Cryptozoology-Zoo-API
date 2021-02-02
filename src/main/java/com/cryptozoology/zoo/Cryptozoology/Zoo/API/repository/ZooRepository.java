package com.cryptozoology.zoo.Cryptozoology.Zoo.API.repository;


import com.cryptozoology.zoo.Cryptozoology.Zoo.API.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZooRepository extends JpaRepository<Animal,Long> {

    @Query(value = "SELECT * FROM Animal u WHERE u.mood = :mood and u.type = :type",
            nativeQuery = true)
    List<Animal> serachAnimal(@Param("mood") Boolean mood, @Param("type") String type);
}
