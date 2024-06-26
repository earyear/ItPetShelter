package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Animal;
import com.itpetshelter.itpetshelter.domain.Reservation;
import com.itpetshelter.itpetshelter.domain.Shelter;
import com.itpetshelter.itpetshelter.dto.ReservationDTO;
import com.itpetshelter.itpetshelter.repository.AnimalRepository;
import com.itpetshelter.itpetshelter.repository.ReservationRepository;
import com.itpetshelter.itpetshelter.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    public void ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public Reservation saveReservation2(ReservationDTO reservationDTO) {
        log.info("ReservationServiceImpl 확인1 전:reservationDTO " +reservationDTO );
        Optional<Animal> resultAnimal = animalRepository.findByAname(reservationDTO.getAname());
        Animal animal = resultAnimal.orElseThrow();
        Optional<Shelter> resultShelter = shelterRepository.findBySname(reservationDTO.getSname());
        Shelter shelter = resultShelter.orElseThrow();
        Reservation reservation = dtoToEntity2(reservationDTO,animal, shelter);
        Reservation result = reservationRepository.save(reservation);
        log.info("ReservationServiceImpl 확인2 후:result " +result );
        return result;

    }

    @Override
    public Long register(ReservationDTO reservationDTO) {
        Reservation reservation = dtoToEntity(reservationDTO);
        Long Rno = reservationRepository.save(reservation).getRno();
        return Rno;
    }

    @Override
    public ReservationDTO read(Long Rno) {
        Optional<Reservation> result = reservationRepository.findById(Rno);
        Reservation reservation = result.orElseThrow();
        ReservationDTO reservationDTO = entityToDTO(reservation);
        return reservationDTO;
    }


}