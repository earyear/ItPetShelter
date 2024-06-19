package com.itpetshelter.itpetshelter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itpetshelter")
@Log4j2
@RequiredArgsConstructor
public class IPSController {
    @GetMapping("/base")
    public String base() {
        return "layout/base";
    }
    
    // 보호소 위치
    @GetMapping("/location")
    public String loaction() {
        return "page/location";
    }

//    봉사활동 신청폼
    @GetMapping("/volunteer")
    public String volunteer() {
        return "page/volunteer";
    }
//    봉사활동 신청서 처리






}
