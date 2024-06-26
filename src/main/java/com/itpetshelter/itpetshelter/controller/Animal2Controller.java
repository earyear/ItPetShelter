package com.itpetshelter.itpetshelter.controller;

import com.itpetshelter.itpetshelter.dto.Animal2DTO;
import com.itpetshelter.itpetshelter.service.AnimalService22;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/itpetshelter")
@Log4j2
@RequiredArgsConstructor
public class Animal2Controller {
    @Autowired
    private AnimalService22 animalService;

    @GetMapping("/animalList22")
    public String getAllAnimals(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Animal2DTO> animalList2;
        if (keyword != null && !keyword.isEmpty()) {
            animalList2 = animalService.searchAnimals(keyword);
        } else {
            animalList2 = animalService.getAllAnimals();
        }
        model.addAttribute("animalList2", animalList2);
        return "itpetshelter/animalList22";
    }

    @GetMapping("/animalDetail")
    public String read(Long ano, Model model) {
        Animal2DTO animalDTO = animalService.read(ano);

        model.addAttribute("animal2DTO", animalDTO);
        return "itpetshelter/animalDetail";
    }

    // GET 요청: 동물 등록 페이지를 표시
    @GetMapping("/animalInsert")
    public String showInsertForm(Model model) {
        model.addAttribute("animal2DTO", new Animal2DTO());
        return "/itpetshelter/animalInsert"; // animalInsert.html 템플릿으로 이동
    }

    @PostMapping("/animalInsert")
    public String insert(@Valid Animal2DTO animalDTO, BindingResult bindingResult, Model model) {
        //유효성 체크
        if (bindingResult.hasErrors()) {
            log.info("오류발생 : " + bindingResult.getAllErrors());
        }
        log.info("화면에서 받은 내용 확인 : "+animalDTO);

        Long ano = animalService.insert(animalDTO);

        return "redirect:/itpetshelter/animalList22";
    }

}