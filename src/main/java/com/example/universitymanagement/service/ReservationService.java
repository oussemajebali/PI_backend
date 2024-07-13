package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Reservation;
import com.example.universitymanagement.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(int id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> getReservationsBySpace(int spaceId) {
        return reservationRepository.findBySpaceId(spaceId);
    }

    public List<Reservation> getReservationsByUser(String userId) {
        return reservationRepository.findByUserId(userId);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(int id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setUserId(reservationDetails.getUserId());
            reservation.setStartTime(reservationDetails.getStartTime());
            reservation.setEndTime(reservationDetails.getEndTime());
            reservation.setSpace(reservationDetails.getSpace());
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }
}
