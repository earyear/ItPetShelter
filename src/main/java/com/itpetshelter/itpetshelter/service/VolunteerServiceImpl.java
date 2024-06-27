package com.itpetshelter.itpetshelter.service;



import com.itpetshelter.itpetshelter.domain.Animal;
import com.itpetshelter.itpetshelter.domain.Reservation;
import com.itpetshelter.itpetshelter.domain.Shelter;
import com.itpetshelter.itpetshelter.domain.Volunteer;
import com.itpetshelter.itpetshelter.dto.VolunteerDTO;
import com.itpetshelter.itpetshelter.repository.ShelterRepository;
import com.itpetshelter.itpetshelter.repository.VolunteerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional

public class VolunteerServiceImpl implements VolunteerService {

    private final ShelterRepository shelterRepository;
    private final VolunteerRepository volunteerRepository;

    @Override
    public Volunteer saveVolunteer(VolunteerDTO volunteerDTO) {
        log.info("ReservationServiceImpl 확인1 전:reservationDTO " +volunteerDTO );
        Optional<Shelter> resultShelter = shelterRepository.findBySname(volunteerDTO.getSname());
        Shelter shelter = resultShelter.orElseThrow();
        Volunteer volunteer = dtoToEntity(volunteerDTO, shelter);
        Volunteer result = volunteerRepository.save(volunteer);
        log.info("ReservationServiceImpl 확인2 후:result " +result );
        return result;
    }

}