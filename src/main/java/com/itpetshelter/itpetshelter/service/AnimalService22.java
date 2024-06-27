package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Animal;
import com.itpetshelter.itpetshelter.dto.Animal2DTO;

import java.util.List;

public interface AnimalService22 {

    List<Animal2DTO> getAllAnimals();
    Animal2DTO read(Long ano);
    Animal2DTO entityToDTO(Animal animal);

    List<Animal2DTO> searchAnimals(String keyword);

    Animal dtoToEntity(Animal2DTO animal2DTO);
    Long insert(Animal2DTO animal2DTO);

    void delete(Long ano);
    void update(Animal2DTO animal2DTO);




}
