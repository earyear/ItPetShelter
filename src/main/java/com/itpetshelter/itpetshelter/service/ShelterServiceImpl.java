package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Shelter;
import com.itpetshelter.itpetshelter.domain.Volunteer;
import com.itpetshelter.itpetshelter.dto.ShelterDTO;
import com.itpetshelter.itpetshelter.dto.VolunteerDTO;
import com.itpetshelter.itpetshelter.repository.ShelterRepository;
import com.itpetshelter.itpetshelter.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelterServiceImpl implements ShelterService{

    @Autowired
    private ShelterRepository shelterRepository;

    @Override
    public List<ShelterDTO> getAllshelters() {

         return shelterRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShelterDTO entityToDTO(Shelter shelter) {
        ShelterDTO shelterDTO = ShelterDTO.builder()
                .Slocate(shelter.getSlocate())
                .Sno(shelter.getSno())
//                .Mno(shelter.getManager().getMno())
                .Sname(shelter.getSname())
                .build();

        return shelterDTO;
    }

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public List<VolunteerDTO> getAllvolunteer() {

        return volunteerRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VolunteerDTO entityToDTO(Volunteer volunteer) {
        VolunteerDTO volunteerDTO = VolunteerDTO.builder()
                .Vno(volunteer.getVno())
                .Sno(volunteer.getShelter().getSno())
//                .Mno(volunteer.getManager().getMno())
                .Cid(volunteer.getConsumer().getCid())
                .VDate(volunteer.getVDate())
                .VTime(volunteer.getVTime())
                .build();

        return volunteerDTO;
    }
}
