package com.itpetshelter.itpetshelter.service;


import com.itpetshelter.itpetshelter.domain.Animal;
import com.itpetshelter.itpetshelter.domain.Reservation;
import com.itpetshelter.itpetshelter.domain.Shelter;
import com.itpetshelter.itpetshelter.domain.Volunteer;
import com.itpetshelter.itpetshelter.dto.ReservationDTO;
import com.itpetshelter.itpetshelter.dto.ShelterDTO;
import com.itpetshelter.itpetshelter.dto.VolunteerDTO;

import java.util.List;

public interface VolunteerService {
    Volunteer saveVolunteer(VolunteerDTO volunteerDTO);

    default Volunteer dtoToEntity(VolunteerDTO volunteerDTO,Shelter shelter) {

        Volunteer reservation = Volunteer.builder()
                .shelter(shelter)
                .Vno(volunteerDTO.getVno())
                .Vdate(volunteerDTO.getVdate())
                .Vtime(volunteerDTO.getVtime())
                .build();
        return reservation;
    }
}




