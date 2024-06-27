package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Reservation;
import com.itpetshelter.itpetshelter.dto.ReservationDTO;

public interface ReservationService {
    void saveReservation(Reservation reservation);

    Reservation saveReservation2(ReservationDTO reservationDTO);

    Long register(ReservationDTO reservationDTO);

    ReservationDTO read(Long Rno);

    default Reservation dtoToEntity(ReservationDTO reservationDTO) {

        Reservation reservation = Reservation.builder()
                .Rno(reservationDTO.getRno())
                .Rdate(reservationDTO.getRdate2())
                .Rtime(reservationDTO.getRtime())
                .build();
        return reservation;
    }

    default ReservationDTO entityToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .Rno(reservation.getRno())
                .Ano(reservation.getAnimal().getAno())
                .Sno(reservation.getShelter().getSno())
                .Rdate2(reservation.getRdate())
                .Rtime(reservation.getRtime())
                .regDate(reservation.getRegDate())
                .modDate(reservation.getModDate())
                .build();

        return reservationDTO;
    }

}