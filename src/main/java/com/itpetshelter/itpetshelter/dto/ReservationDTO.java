package com.itpetshelter.itpetshelter.dto;

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

    private String Aname;

    private String Cid;

    private String Sname;

    private LocalDate Rdate;
    private String Rtime;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}