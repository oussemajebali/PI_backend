package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.Reservation;
import com.example.universitymanagement.entity.Space;
import com.example.universitymanagement.service.ReservationService;
import com.example.universitymanagement.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.universitymanagement.dto.ReservationWrapper;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private SpaceService spaceService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping("/space/{spaceId}")
    public List<Reservation> getReservationsBySpace(@PathVariable int spaceId) {
        return reservationService.getReservationsBySpace(spaceId);
    }

    @GetMapping("/user/{user}")
    public List<Reservation> getReservationsByUser(@PathVariable int user) {
        return reservationService.getReservationsByUser(user);
    }

    //@PostMapping
    //public Reservation createReservation(@RequestBody Reservation reservation) {
      //  return reservationService.createReservation(reservation);}
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationWrapper reservationWrapper) {
        // Fetch the space entity using the provided spaceId
        Space space = spaceService.getSpaceById(reservationWrapper.getSpaceId());
        if (space == null) {
            return ResponseEntity.badRequest().build();
        }

        // Create and populate the reservation entity
        Reservation reservation = new Reservation();
        reservation.setUserId(reservationWrapper.getUserId());
        reservation.setStartDate(LocalDate.parse(reservationWrapper.getStartDate()));
        reservation.setEndDate(LocalDate.parse(reservationWrapper.getEndDate()));
        reservation.setSpace(space);

        // Save the reservation
        Reservation createdReservation = reservationService.saveReservation(reservation);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
