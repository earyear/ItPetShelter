package com.itpetshelter.itpetshelter.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class Volunteer extends BaseEntity{
    @Id

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Vno;

    @Column(length = 20,nullable = false)
    private int Mno;

    @Column(length = 20,nullable = false)
    private String Uid;

    @Column(name = "date", updatable = false)
    private LocalDateTime Date;

    @Column(name = "time", updatable = false)
    private LocalDateTime Time;





}
