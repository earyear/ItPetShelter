package com.itpetshelter.itpetshelter.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Shelter {
    @Id
    private int Sno;

    @OneToOne
    @JoinColumn(name = "Mno")
    private Manager manager;

    private String Sname;
    private String Slocate;
}
