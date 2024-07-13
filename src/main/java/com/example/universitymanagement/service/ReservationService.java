package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Reservation;
import com.example.universitymanagement.entity.Space;
import com.example.universitymanagement.entity.User;
import com.example.universitymanagement.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private MailgunEmailService emailService;

    @Autowired
    private UserService userService;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(int id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> getReservationsBySpace(int spaceId) {
        return reservationRepository.findBySpaceId(spaceId);
    }

    public List<Reservation> getReservationsByUser(int userId) {
        return reservationRepository.findByUserId(userId);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(int id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            reservation.setUserId(reservationDetails.getUserId());
            reservation.setStartDate(reservationDetails.getStartDate());
            reservation.setEndDate(reservationDetails.getEndDate());
            reservation.setSpace(reservationDetails.getSpace());
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public Reservation saveReservation(Reservation reservation) {
        Space space = spaceService.getSpaceById(reservation.getSpace().getId());
        reservation.setSpace(space);
        Reservation savedReservation = reservationRepository.save(reservation);
        try {
            sendReservationEmail(savedReservation, "Reservation Created", "Your reservation has been created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return savedReservation;
    }

    public void deleteReservation(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            reservationRepository.deleteById(reservationId);
            try {
                sendReservationEmail(reservation, "Reservation Deleted", "Your reservation has been deleted.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendReservationEmail(Reservation reservation, String subject, String text) throws IOException {
        User user = userService.getUserById(reservation.getUserId());
        if (user != null) {
            String userEmail = user.getEmail();
            String emailText = text + "\n\nDetails:\n" +
                    "Reservation ID: " + reservation.getId() + "\n" +
                    "Start Date: " + reservation.getStartDate() + "\n" +
                    "End Date: " + reservation.getEndDate() + "\n" +
                    "Space: " + reservation.getSpace().getName();
            emailService.sendEmail(userEmail, subject, emailText);
        }
    }
}
