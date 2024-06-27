package com.itpetshelter.itpetshelter.service;

import com.itpetshelter.itpetshelter.domain.Animal;
import com.itpetshelter.itpetshelter.domain.Reservation;
import com.itpetshelter.itpetshelter.domain.Shelter;
import com.itpetshelter.itpetshelter.dto.ReservationDTO;

public interface ReservationService {
    void saveReservation(Reservation reservation);

    Reservation saveReservation2(ReservationDTO reservationDTO);

    Long register(ReservationDTO reservationDTO);

    ReservationDTO read(Long Rno);



    default Reservation dtoToEntity2(ReservationDTO reservationDTO, Animal animal, Shelter shelter) {

        Reservation reservation = Reservation.builder()
                .animal(animal)
                .shelter(shelter)
                .Rno(reservationDTO.getRno())
                .Rdate(reservationDTO.getRdate())
                .Rtime(reservationDTO.getRtime())
                .build();
        return reservation;
    }

    default Reservation dtoToEntity(ReservationDTO reservationDTO) {

        Reservation reservation = Reservation.builder()
                .Rno(reservationDTO.getRno())
                .Rdate(reservationDTO.getRdate())
                .Rtime(reservationDTO.getRtime())
                .build();
        return reservation;
    }

    default ReservationDTO entityToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = ReservationDTO.builder()
                .Rno(reservation.getRno())
                .Aname(reservation.getAnimal().getAname())
                .Sname(reservation.getShelter().getSname())
                .Rdate(reservation.getRdate())
                .Rtime(reservation.getRtime())
                .regDate(reservation.getRegDate())
                .modDate(reservation.getModDate())
                .build();

        return reservationDTO;
    }

}