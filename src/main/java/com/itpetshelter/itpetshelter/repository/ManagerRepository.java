package com.itpetshelter.itpetshelter.repository;

import com.itpetshelter.itpetshelter.domain.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, String> {

    // 소셜 로그인이 아닌, 일반 로그인으로 검색하기.
    // N+1,한번에 다같이 조회를 하자. in 연산자 이용해서, 하나의 쿼리로 동작하기.
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Manager m where m.Mid = :Mid ")
    Optional<Manager> getWithRoles(String Mid);



    //DML 적용하기
    @Modifying
    @Transactional
    @Query("update Manager m set m.Mpw=:Mpw where m.Mid = :Mid")
    void updatePassword(@Param("Mpw") String Mpw, @Param("Mid") String Mid);

}
