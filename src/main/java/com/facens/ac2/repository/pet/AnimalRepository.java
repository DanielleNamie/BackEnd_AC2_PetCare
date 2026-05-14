package com.facens.ac2.repository.pet;

import com.facens.ac2.entity.pet.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {}
