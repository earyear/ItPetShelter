package repository;


import com.itpetshelter.itpetshelter.domain.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShelterRepository extends JpaRepository<Shelter,Long> {

    Page<Shelter> findByTitleContainingOrderByBnoDesc(String sname, String slocate);
    @Query("select  s from Shelter s where s.Sname  like concat('%',:keyword ,'%')")
    Page<Shelter> findByKeyword(String keyword, Pageable pageable);


}
