package com.itpetshelter.itpetshelter.repository;

import com.itpetshelter.itpetshelter.domain.Animal;
import com.itpetshelter.itpetshelter.domain.Manager;
import com.itpetshelter.itpetshelter.domain.Shelter;
import com.itpetshelter.itpetshelter.domain.Type;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class AnimalRepositoryTest {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    ShelterRepository shelterRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Test
    public void testInsert() {
        //실제 디비 각자 데이터에 따라서 다름.
        // 현재 bno = 900
        Long tno = 4L;

        Type type = Type.builder()
                .type("야옹이")
                .build();

        type = typeRepository.save(type);

        Shelter shelter = Shelter.builder()
                .slocate("test4")
                .sname("test4")
                .build();


        shelter = shelterRepository.save(shelter);

        Manager manager = Manager.builder()
                .shelter(shelter)
                .Mpw("test")
                .Mname("test")
                .Mid("test")
                .build();

        manager = managerRepository.save(manager);


        Animal animal = Animal.builder()
                .type(type)
                .shelter(shelter)
                .manager(manager)
                .Aage(3L)
                .Adisease(true)
                .Aneutered(true)
                .Aname("뽀삐4")
                .build();



        Animal result = animalRepository.save(animal);




        log.info("더미 데이터 확인 : "+result);
    } //
    @Test
    @Transactional
    public void selectAnimal() {
        Optional<Animal> result = animalRepository.findByAname("뽀삐");
        Animal animal = result.get();
        log.info("animal : "+animal);
    }
}

