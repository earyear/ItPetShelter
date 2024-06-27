package com.itpetshelter.itpetshelter.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ReservationDTO {

    private Long Rno;

    private Long Mno;

    private Long Ano;

    private String Cid;

    private Long Sno;

    private LocalDateTime Rdate;
    private LocalDate Rdate2;
    private String Rtime;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}