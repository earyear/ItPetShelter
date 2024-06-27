package com.itpetshelter.itpetshelter.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Animal")
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Ano;

    // 연관 관계 설정 메서드 추가
//    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Tno")
    private Type type;

//    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Sno")
    private Shelter shelter;




    @ManyToOne
    @JoinColumn(name = "Mno")
    private Manager manager;

    private String Aname;
    private Long Aage;
    private Boolean Agender;   //성별
    private Boolean Aneutered; //중성화
    private Boolean Adisease;  //질병

    private String Acontent; //동물 소개 설명글




    //========================이미지 처리

    //cascadeType.all > 부모 테이블의 1차 캐시 테이블에 작업 시, 하위 테이블도 적용
    //FetchType.EAGER > DB에 즉시 로딩(default), LAZY > DB에 조금 늦게 반영함.
    //orphanRemoval = true > 부모가 null이면 자동 삭제함.
    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20) // 테이블 조회 시 매번 DB연결에 자원 소모 발생(N+1) > 지정 갯수 한번에 처리
    private Set<AnimalImage> imageSet = new HashSet<>();

    //이미지 추가
    public void addImage(String uuid, String fileName) {
        AnimalImage animalImage = AnimalImage.builder()
                .Auuid(uuid)
                .AfileName(fileName)
                .ord(imageSet.size())
                .animal(this)
                .build();

        imageSet.add(animalImage);
    }

    //이미지 삭제
    public void clearImage(){
        imageSet.forEach(Image -> Image.changeAnimal(null));
        this.imageSet.clear();
    }
}
