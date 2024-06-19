package com.itpetshelter.itpetshelter.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Volunteer", indexes = {
        @Index(name = "idx_Volunteer_Shelter_Sno", columnList = "Shelter_Sno")
})
@Entity
public class Volunteer extends BaseEntity{
    @Id
    private int Vno;

    @OneToOne
    @JoinColumn(name = "Sno")
    private Shelter shelter;

    @OneToOne
    @JoinColumn(name = "Mno")
    private Manager manager;

    @OneToOne
    @JoinColumn(name = "Uid")
    private User user;

    private LocalDateTime Date;
    private LocalDateTime Time;
}
