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

public class VolunteerDTO {
    private Long Vno;
    private String Sname;
    private Long Mno;
    private String Cid;

    private LocalDate Vdate;
    private String Vtime;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
