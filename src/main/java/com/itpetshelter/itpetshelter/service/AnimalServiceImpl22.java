package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Animal;
import com.itpetshelter.itpetshelter.domain.Shelter;
import com.itpetshelter.itpetshelter.domain.Type;
import com.itpetshelter.itpetshelter.dto.Animal2DTO;
import com.itpetshelter.itpetshelter.repository.AnimalRepository;
import com.itpetshelter.itpetshelter.repository.ShelterRepository;
import com.itpetshelter.itpetshelter.repository.TypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class AnimalServiceImpl22 implements AnimalService22 {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private final TypeRepository typeRepository;
//    private final ShelterRepository22 shelterRepository;
    @Autowired
    private final ShelterRepository shelterRepository;

    @Autowired
    public AnimalServiceImpl22(TypeRepository typeRepository, ShelterRepository shelterRepository) {
        this.typeRepository = typeRepository;
        this.shelterRepository = shelterRepository;
    }

    @Override
    public List<Animal2DTO> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Animal2DTO read(Long ano) {
        return animalRepository.findById(ano)
                .map(this::entityToDTO)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
    }

    @Override
    public Long insert(Animal2DTO animal2DTO) {
        Animal animal = dtoToEntity(animal2DTO);
        Long ano = animalRepository.save(animal).getAno();
        return ano;
    }


    @Override
    public Animal2DTO entityToDTO(Animal animal) {

        Animal2DTO animal2DTO = Animal2DTO.builder()
                .Ano(animal.getAno())
                .Atype(animal.getType().getType())
                .Aname(animal.getAname())
                .Aage(animal.getAage())
                .Acontent(animal.getAcontent())
                .Agender(animal.getAgender())
                .Aneutered(animal.getAneutered())
                .Adisease(animal.getAdisease())
                .Alocate(animal.getShelter().getSlocate())
                .Alocatename(animal.getShelter().getSname())
                .build();

        //첨부된 이미지 파일
        List<String> imageFileNames = animal.getImageSet().stream()
                .map(image -> image.getAuuid() + "_" + image.getAfileName())
                .collect(Collectors.toList());

        animal2DTO.setImageFileNames(imageFileNames);

        return animal2DTO;

    }


    @Override
    public Animal dtoToEntity(Animal2DTO animal2DTO) {

        Type type = typeRepository.findByType(animal2DTO.getAtype())
                .orElseGet(()->{
                   Type newType = Type.builder()
                           .type(animal2DTO.getAtype())
                           .build();

                   return typeRepository.save(newType);
                });


        Shelter shelter = shelterRepository.findBySnameAndSlocate(animal2DTO.getAlocatename(),animal2DTO.getAlocate())
                .orElseGet(()->{
                    Shelter newShelter = Shelter.builder()
                            .sname(animal2DTO.getAlocatename())
                            .slocate(animal2DTO.getAlocate())
                            .build();

                    return shelterRepository.save(newShelter);
                });

        Animal animal = Animal.builder()
                .Ano(animal2DTO.getAno())
                .type(type)
                .Aname(animal2DTO.getAname())
                .Aage(animal2DTO.getAage())
                .Acontent(animal2DTO.getAcontent())
                .Agender(animal2DTO.getAgender())
                .Adisease(animal2DTO.getAdisease())
                .Aneutered(animal2DTO.getAneutered())
                .shelter(shelter)
                .build();


        // 첨부 이미지들 추가
        if(animal2DTO.getImageFileNames() != null) {
            animal2DTO.getImageFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_");
                animal.addImage(arr[0],arr[1]);
            });
        }
        return animal;
    }

    @Override
    public List<Animal2DTO> searchAnimals(String keyword) {
        List<Animal> animals = animalRepository.findByTypeKeyword(keyword);
        return animals.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

}


