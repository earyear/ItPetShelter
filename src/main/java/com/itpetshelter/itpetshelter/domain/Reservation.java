package com.itpetshelter.itpetshelter.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Reservation")
@Entity
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Rno;

    @ManyToOne
    @JoinColumn(name = "Mno")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "Ano")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name="Cid")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "Sno")
    private Shelter shelter;

    private LocalDate Rdate;
    private String Rtime;

}