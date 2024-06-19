package com.itpetshelter.itpetshelter.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Reservation", indexes = {
        @Index(name = "idx_Reservation_Animal_Ano", columnList = "Animal_Ano")
})
@Entity
public class Reservation extends com.itpetshelter.itpetshelter.domain.BaseEntity {
    @Id
    private int Rno;

    @OneToOne
    @JoinColumn(name = "Mno")
    private Manager manager;

    @OneToOne
    @JoinColumn(name = "Ano")
    private com.itpetshelter.itpetshelter.domain.Animal animal;

    @OneToOne
    @JoinColumn(name = "Uid")
    private User user;

    @OneToOne
    @JoinColumn(name = "Sno")
    private com.itpetshelter.itpetshelter.domain.Shelter shelter;

    private LocalDateTime Date;
    private LocalDateTime Time;
    private String R_check;
}

