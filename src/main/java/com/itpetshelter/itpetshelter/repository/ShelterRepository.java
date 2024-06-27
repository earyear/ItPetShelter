package com.itpetshelter.itpetshelter.repository;

import com.itpetshelter.itpetshelter.domain.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Optional<Shelter> findBySnameAndSlocate(String Sname, String Slocate);

}
