package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Reservation;
import com.itpetshelter.itpetshelter.dto.ReservationDTO;
import com.itpetshelter.itpetshelter.repository.ReservationRepository;
import jakarta.transaction.Transactional;
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
    public void ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public Reservation saveReservation2(ReservationDTO reservationDTO) {
        Reservation reservation = dtoToEntity(reservationDTO);
        Reservation result = reservationRepository.save(reservation);
        log.info("ReservationServiceImpl 확인1:result " +result );
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