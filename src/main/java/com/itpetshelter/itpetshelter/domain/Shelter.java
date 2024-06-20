package com.itpetshelter.itpetshelter.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class Shelter extends BaseEntity{
    @Id

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Sno;

    @Column(length = 15, nullable = false)
    private int Mno;


    @Column(length = 15, nullable = false)
    private String Sname;

    @Column(length = 50, nullable = false)
    private String Slocate;


}