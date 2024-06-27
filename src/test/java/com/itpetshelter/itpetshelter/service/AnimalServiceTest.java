package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Animal;
import com.itpetshelter.itpetshelter.dto.Animal2DTO;
import com.itpetshelter.itpetshelter.dto.AnimalDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
public class AnimalServiceTest {

    @Autowired
    AnimalService22 animalService22;

    @Test
    public void testupdate() {

        Animal2DTO animal2DTO = Animal2DTO.builder()
                .Ano(1L)
                .Aage(7L)
                .Aname("푸들이었던것")
                .Acontent("테스트입니다")
                .Atype("푸들이었을걸?")
                .Adisease(true)
                .Agender(true)
                .Aneutered(true)
                .Alocate("테스트")
                .Alocatename("테스트")
                .build();

        animalService22.update(animal2DTO);

    }

}

