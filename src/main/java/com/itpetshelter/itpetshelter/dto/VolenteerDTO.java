package com.itpetshelter.itpetshelter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class VolenteerDTO {

    private long Vno;
    private long Sno;
    private long Mno;
    private String Uid;
    private LocalDateTime Date;
    private LocalDateTime Time;

}
