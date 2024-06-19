package com.itpetshelter.itpetshelter.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Board", indexes = {
        @Index(name = "idx_Board_User_Uid", columnList = "User_Uid")
})
@Entity
public class Board extends BaseEntity {
    @Id
    private int Bno;

    @OneToOne
    @JoinColumn(name = "Uid")
    private User user;

    private String B_Title;

    private String B_Memo;
    private LocalDateTime DateTime;

    public void changeTitleAndContent(String B_Title, String B_Memo) {
        this.B_Title = B_Title;
        this.B_Memo = B_Memo;
    }
}


