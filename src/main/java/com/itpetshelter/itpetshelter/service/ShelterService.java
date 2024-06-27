package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Shelter;
import com.itpetshelter.itpetshelter.domain.Volunteer;
import com.itpetshelter.itpetshelter.dto.ShelterDTO;
import com.itpetshelter.itpetshelter.dto.VolunteerDTO;

import java.util.List;

public interface ShelterService {

    List<ShelterDTO> getAllshelters();
    ShelterDTO entityToDTO(Shelter shelter);

    List<VolunteerDTO> getAllvolunteers();
    VolunteerDTO entityToDTO2(Volunteer volunteer);
}
